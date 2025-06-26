import { useState } from 'react';
import LoginForm from './components/LoginForm';
import SignupForm from './components/SignupForm';
import PublicMessages from './components/PublicMessages';
import HomePage from './components/HomePage';
import ClassPage from './components/ClassPage';
import LearningPlan from './components/LearningPlan';

export default function App() {
  const [token, setToken] = useState(null);
  const [view, setView] = useState('login');
  const [privateMsg, setPrivateMsg] = useState('');
  const [page, setPage] = useState('home');

  const loadPrivate = async () => {
    if (!token) return;
    const res = await fetch('http://localhost:8080/private', {
      headers: { Authorization: `Bearer ${token}` }
    });
    const txt = await res.text();
    setPrivateMsg(txt);
  };

  if (!token) {
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
        <PublicMessages />
      </div>
    );
  }

  return (
    <div>
      <h1>StudyBuddy</h1>
      <nav>
        <button onClick={() => setPage('home')}>Home</button>
        <button onClick={() => setPage('class')}>Class</button>
        <button onClick={() => setPage('plan')}>Learning Plan</button>
      </nav>

      {page === 'home' && <HomePage />}
      {page === 'class' && <ClassPage />}
      {page === 'plan' && <LearningPlan />}

      <div>
        <button onClick={loadPrivate}>Load Private Message</button>
        <p>{privateMsg}</p>
      </div>

      <PublicMessages />
    </div>
  );
}
