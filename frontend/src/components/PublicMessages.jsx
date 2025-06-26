import { useEffect, useState } from 'react';

export default function PublicMessages() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');

  const loadMessages = async () => {
    const res = await fetch('http://localhost:8080/public');
    const data = await res.json();
    setMessages(data);
  };

  useEffect(() => {
    loadMessages();
  }, []);

  const addMessage = async () => {
    await fetch('http://localhost:8080/public', {
      method: 'POST',
      headers: { 'Content-Type': 'text/plain' },
      body: input
    });
    setInput('');
    loadMessages();
  };

  return (
    <div>
      <h2>Public Messages</h2>
      <ul>
        {messages.map((m, idx) => <li key={idx}>{m}</li>)}
      </ul>
      <input value={input} onChange={(e) => setInput(e.target.value)} />
      <button onClick={addMessage}>Post</button>
    </div>
  );
}
