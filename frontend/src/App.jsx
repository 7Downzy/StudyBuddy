import { useState } from 'react';
import LoginForm from './components/LoginForm';
import SignupForm from './components/SignupForm';
import PublicMessages from './components/PublicMessages';

export default function App() {
  const [token, setToken] = useState(null);
  const [view, setView] = useState('login');
  const [privateMsg, setPrivateMsg] = useState('');

  const loadPrivate = async () => {
    if (!token) return;
    const res = await fetch('http://localhost:8080/private', {
      headers: { Authorization: `Bearer ${token}` }
    });
    const txt = await res.text();
    setPrivateMsg(txt);
  };

  return (
    <div>
      <h1>StudyBuddy</h1>
      {view === 'login' && (
        <LoginForm onSuccess={(t) => { setToken(t); setView('private'); }}
                   onSwitch={() => setView('signup')} />
      )}
      {view === 'signup' && (
        <SignupForm onSwitch={() => setView('login')} />
      )}
      {token && (
        <div>
          <button onClick={loadPrivate}>Load Private Message</button>
          <p>{privateMsg}</p>
        </div>
      )}
      <PublicMessages />
    </div>
  );
}
