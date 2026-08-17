import Keycloak from "keycloak-js";

const keycloakConfig = {
  url: "http://localhost:8080",
  realm: "ems-realm",
  clientId: "ems-frontend",
};

const keycloak = new Keycloak(keycloakConfig);

export const doLogin = () => keycloak.login();
export const doLogout = () => keycloak.logout({ redirectUri: window.location.origin });

// Automatically updates token if it has expired or is about to expire
export const getValidToken = async () => {
  try {
    await keycloak.updateToken(30); 
    return keycloak.token;
  } catch (error) {
    console.error("Failed to refresh token, logging out...", error);
    doLogout();
  }
};

export const getUsername = () => keycloak.tokenParsed?.preferred_username;

export default keycloak;
