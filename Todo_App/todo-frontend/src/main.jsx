import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./index.css";
import keycloak from "./services/keycloak";

keycloak
  .init({
    onLoad: "login-required",
    checkLoginIframe: false
  })
  .then(authenticated => {

    if (!authenticated) {
      console.error("User is not authenticated");
      return;
    }

    console.log("Keycloak authentication successful");
    console.log("Token available:", !!keycloak.token);

    ReactDOM.createRoot(
      document.getElementById("root")
    ).render(
      <React.StrictMode>
        <App />
      </React.StrictMode>
    );
  })
  .catch(error => {
    console.error("Keycloak initialization failed:", error);
  });