package org.eduorg.msauth.auth.infraestructure.repository;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tokens")
public class Token {

    @Id
    @Generated("uuid")
    private String id;

    private String token;

    @Builder.Default
    private TokenType tokenType = TokenType.BEARER;

    private boolean isRevoked;

    private boolean isExpired;

    private String userId;

    public enum TokenType {
        BEARER,
    }
}
