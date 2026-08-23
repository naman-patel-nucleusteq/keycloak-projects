import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    url: "http://localhost:8080",
    realm: "ems-realm",
    clientId: "todo-frontend"
});

export default keycloak;