import React, { createContext, useContext, useEffect, useState } from 'react';
import keycloak from './keycloak'; 

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [isInitialized, setIsInitialized] = useState(false);

  useEffect(() => {
    keycloak
      .init({
        onLoad: 'login-required', // Redirects to Keycloak if not logged in
        checkLoginIframe: false,   
      })
      .then((authenticated) => {
        if (authenticated) {
          setIsInitialized(true);
        } else {
          // If Keycloak fails to authenticate, force a reload to try again
          window.location.reload();
        }
      })
      .catch((err) => {
        console.error('Keycloak initialization failed:', err);
      });
  }, []);

  // Show a loading screen while Keycloak sets up cookies/tokens
  if (!isInitialized) {
    return (
      <div className="flex min-h-screen items-center justify-center bg-gray-900 text-white font-sans">
        <div className="text-center">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-indigo-500 mx-auto mb-4"></div>
          <p className="text-lg font-medium text-slate-300">Securing your session...</p>
        </div>
      </div>
    );
  }

  return (
    <AuthContext.Provider value={{ keycloak }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
