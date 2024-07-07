package com.example.identityservice.service;

import com.example.identityservice.dto.ResponseMessage;
import com.example.identityservice.dto.User;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Response;
import java.util.Arrays;


@Service
public class KeycloakService {
    

    public Mono<Object[]> createUser(User user) {
        return Mono.defer(() -> {
            ResponseMessage message = new ResponseMessage();
            int statusId = 0;

            try {
                UsersResource usersResource = getUsersResource();
                UserRepresentation userRepresentation = new UserRepresentation();
                userRepresentation.setUsername(user.getUsername());
                userRepresentation.setEmail(user.getEmail());
                userRepresentation.setFirstName(user.getFirstName());
                userRepresentation.setLastName(user.getLastName());
                userRepresentation.setEnabled(true);
                userRepresentation.setEmailVerified(true);

                Response result = usersResource.create(userRepresentation);
                statusId = result.getStatus();

                if (statusId == 201) {
                    String path = result.getLocation().getPath();
                    String userId = path.substring(path.lastIndexOf("/") + 1);
                    CredentialRepresentation passwordCredential = new CredentialRepresentation();
                    passwordCredential.setTemporary(false);
                    passwordCredential.setType(CredentialRepresentation.PASSWORD);
                    passwordCredential.setValue(user.getPassword());
                    usersResource.get(userId).resetPassword(passwordCredential);

                    RealmResource realmResource = getRealmResource();
                    RoleRepresentation roleRepresentation = realmResource.roles().get("user").toRepresentation();
                    realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));
                    message.setMessage("Usuario creado con éxito");
                } else if (statusId == 409) {
                    message.setMessage("Ese usuario ya existe");
                } else {
                    message.setMessage("Error creando el usuario");
                }
            } catch (Exception e) {
                return Mono.error(e);
            }

            return Mono.just(new Object[]{statusId, message});
        });
    }


    private RealmResource getRealmResource(){
        Keycloak kc = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8090/realms/PRS")
                .realm("master")
                .username("admin")
                .password("admin")
                .clientId("admin-cli")
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(10)
                        .build())
                .build();
        return kc.realm("PRS");
    }

    private UsersResource getUsersResource(){
        RealmResource realmResource = getRealmResource();
        return realmResource.users();
    }
}
