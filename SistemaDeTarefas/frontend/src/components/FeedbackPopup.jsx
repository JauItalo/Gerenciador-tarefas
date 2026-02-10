import React from 'react';

function FeedbackPopup({ message, onClose }) {
  if (!message) return null;

  return (
    <div style={{
      position: 'fixed',
      top: '30px',
      left: '50%',
      transform: 'translateX(-50%)',
      background: '#333',
      color: '#fff',
      padding: '16px 32px',
      borderRadius: '8px',
      zIndex: 1000,
      boxShadow: '0 2px 8px rgba(0,0,0,0.2)',
      display: 'flex',
      alignItems: 'center'
    }}>
      <span>{message}</span>
      <button
        onClick={onClose}
        style={{
          marginLeft: '20px',
          background: 'transparent',
          color: '#fff',
          border: 'none',
          fontSize: '18px',
          cursor: 'pointer'
        }}
        aria-label="Fechar"
      >
        ×
      </button>
    </div>
  );
}

export default FeedbackPopup;