package utils

import (
	"github.com/dgrijalva/jwt-go"
	"time"
)

var jwtSecret = []byte("yorick")

type Claims struct {
	ID        uint   `json:"id"`
	Username  string `json:"user_name"`
	Authority int    `json:"authority"`
	jwt.StandardClaims
}

func GenerateToken(id uint, username string, authority int) (tokenString string, err error) {
	nowTime := time.Now()
	expireTime := nowTime.Add(24 * time.Hour)
	claims := Claims{
		ID:        id,
		Username:  username,
		Authority: authority,
		StandardClaims: jwt.StandardClaims{
			ExpiresAt: expireTime.Unix(),
			Issuer:    "go_mall",
		},
	}
	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
	tokenString, err = token.SignedString(jwtSecret)
	return tokenString, err
}
