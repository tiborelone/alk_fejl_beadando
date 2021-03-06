# Alkalmazások fejlesztése beadandó

## Bevezető

Beadandónk egy a Ticketswap ötletén alapuló rendszer, amely lehetőve teszi jegyek biztonságos eladását és vételét a felhasználók között. A jegyek nagyrészt a zenei eseményekhez lesznek meghírdetve. A program ezeknek a tranzakcióknak a lebonyolítására lesz hivatott.

## Projektötlet

### Funkcionális követelmények

1.  Regisztráció
2.  Bejelentkezés
3.  Regisztrált felhasználok
    - Eseményre való keresés
      - Eladó jegyek megtekintése
      - Keresett jegyek megtekintése
      - Jegy lefoglalás és vásárlása
      - Új jegy generálása eladásra
        - Értékelés leadása
    - Általánosan
      - Felhasználói profilok megtekintése
        - Értékelések megtekintése
        - Üzenet küldése
        - Üzenetek megjelenítése
    - Admin
      - Hirdetések moderálása (Törlése, Módosítása)
      - Felhasználó Törlése
      - Felhasználó profil adatainak módósítása

### Nem Funkcionális követelmények

1. Biztonságos: Jelszavas beléptető rendszer, titkosított jelszó tárolás.
2. Megbizhatóság: Adás vétel esetén egy új jegyet generál le a rendszer a visszaélések megakadályozása érdekében.
3. Felhasználóbarát: Intuitív kezelőfelület, kontrasztos színek, gyors működés.

### Fogalomjegyzék

1. Üzenet: A felhasználók közötti kommunikációt teszi elérhetővé.
2. Értékelés: A felhasználók megbizhatóságát reprezentáló érték. Adás-vétel után értékelhető a másik fél.
3. Esemény: Az entitás amelyre a jegyeket meg lehet hirdetni eladásra és vételre.
4. Jegy: Egy egyedi azonosítóval ellátott űrlap, amely egy eseményre való belépés jogát biztosítja.
5. Admin: Az oldal szabályszerű használatát biztosító, teljes joggal rendelkező felhasználó.

### Szerepkörök

1. Felhasználó: lásd funkcionális követelmények
2. Admin: lásd funkcionális követelmények, fogalomjegyzék

### Használati eset diagram

![alt text](https://github.com/tiborelone/alk_fejl_beadando/blob/master/Hasznalati.png)

### Egy jegy meghirdetésének menete

![alt text](https://github.com/tiborelone/alk_fejl_beadando/blob/master/JegyHirdetes.png)

## Tervezés

### Entitások

- User
    - Id (Long)
    - Username (String)
    - Password (String)
    - Name (String)
    - Selling (List<TicketSale>)
    - Buying (List<TicketWanted>)
    - Rating (Integer)
- Event
    - Id (Long)
    - Name (String)
    - Date (String)
    - TicketSale (List<TicketSale>)
    - TicketWanted (List<TicketWanted>)
- TicketSale
    - Id (Long)
    - Barcode (Long)
    - Event (Event)
    - Price (Integer)
    - Seller (User)
- TicketWanted
    - Id (Long)
    - Event (Event)
    - PriceLimit (Integer)
    - Buyer (User)
    
### UML
![alt text](https://github.com/tiborelone/alk_fejl_beadando/blob/master/alk_uml.png)


### Végpontok

- POST/
    - /user: Felhasználó hozzáadása
    - /event: Esemény hozzáadása
    - /ticketsale: Eladó jegy hozzáadása
    - /ticketwanted: Vásárolni kívánt jegy hozzáadása
- PUT/
    - /user/:id : ID-hez tartozó felhasználó módosítása
    - /event/:id : ID-hez tartozó esemény módosítása
    - /ticketsale/:id : ID-hez tartozó eladó jegy módosítása
    - /ticketwanted/:id : ID-hez tartozó vásárolni kívánt jegy módosítása
- DELETE/
    - /user/:id : ID-hez tartozó felhasználó törlése
    - /event/:id : ID-hez tartozó esemény törlése
    - /ticketsale/:id : ID-hez tartozó eladó jegy törlése
    - /ticketwanted/:id : ID-hez tartozó vásárolni kívánt jegy törlése
- GET/
    - /user: Felhasználók megjelenítése
        - /:id : Adott id-hez tartozó felhasználó megjelenítése
            - /:id/selling: ID-hez tartozó felhasználó eladó jegyeinek megjelenítése
            - /:id/buying: ID-hez tartozó felhasználó vásárolni kívánt jegyeinek megjelenítése
            - /:id/rating: ID-hez tartozó felhasználó értékelésének megjelenítése
    - /event: Események megjelenítése
        - /:id : Adott id-hez tartozó esemény megjelenítése
            - /:id/ticketsale: ID-hez tartozó eseményre, az eladó jegyek megjelenítése
            - /:id/ticketwanted: ID-hez tartozó eseményre, a vásárolni kívánt jegyek megjelenítése
    - /ticketsale: Eladó jegyek megjelenítése
        - /:id : Adott id-hez tartozó eladó jegy megjelenítése
            - /:id/event: Eladni kívánt jegyhez tartozó esemény
            - /:id/seller: Jegy eladójának (felhasználó) megtekintése
    - /ticketwanted: Vásárolni kívánt jegyek megjelenítése
        - /:id : ID-hez tartozó vásárolni kívánt jegy megjelenítése
            - /:id/event: Vásárolni kívánt jegyhez tartozó esemény
            - /:id/seller: Jegyet vásárolni kívánó felhasználó megtekintése
            
### Használt eszközök

- Github - verziókezelő
- NetBeans - Java fordító program
- Maven - project management/függőségek kezelése
- Spring/Springboot - keretrendszer
- Visual Studio Code - Lokális IDE
- Node.js - Javascript környezet

### Könyvtárstruktúra

- #### controllers
    - UserController.java
    - EventController.java
    - TicketSaleController.java
    - TicketWantedController.java
- #### entities
    - User.java
    - Event.java
    - TicketSale.java
    - TicketWanted.java
- #### repositories
    - UserRepository.java
    - EventRepository.java
    - TicketSaleRepository.java
    - TicketWantedRepository.java
- #### security
    - AuthenticatedUser.java
    - CustomBasicAuthenticationEntryPoint.java
    - MyUserDetailsService.java
    - WebSecurityConfig.java
- TicketSwapApplication.java

### ---------

- app
    - app-routing.module.ts
    - app.component.css
    - app.component.html
    - app.component.spec.ts
    - app.component.ts
    - app.module.ts
    - auth.guard.spec.ts
    - auth.guard.ts
    - auth.service.spec.ts
    - auth.service.ts
    - event.service.spec.ts
    - ent.service.ts
    - ent.spec.ts
	- event.ts
	- in-memory-data.service.spec.ts
	- in-memory-data.service.ts
	- message.service.spec.ts
	- message.service.ts
	- ticket.service.spec.ts
	- ticket.service.ts
	- ticket.spec.ts
	- ticket.ts
	- user.service.spec.ts
	- user.service.ts
	- user.spec.ts
	- user.ts
   
- dashboard
	- dashboard.component.css
	- dashboard.component.html
	- dashboard.component.spec.ts
	- dashboard.component.ts
      
- event
	- event.component.css
	- event.component.html
	- event.component.spec.ts
	- event.component.ts
       
- event-detail
	- event-detail.component.css
	- event-detail.component.html
	- event-detail.component.spec.ts
	- event-detail.component.ts
	- 
- event-search
	- event-search.component.css
	- event-search.component.html
	- event-search.component.spec.ts
	- event-search.component.ts
	- 
- home
	- home.component.css
	- home.component.html
	- home.component.spec.ts
	- home.component.ts
	- 
- login-form
	- login-form.component.css
	- login-form.component.html
	- login-form.component.spec.ts
	- login-form.component.ts
	- 
- messages
	- messages.component.css
	- messages.component.html
	- messages.component.spec.ts
	- messages.component.ts
	- 
- ticket
	- ticket.component.css
	- ticket.component.html
	- ticket.component.spec.ts
	- ticket.component.ts
	- 
- ticket-detail
	- ticket-detail.component.css
	- ticket-detail.component.html
	- ticket-detail.component.spec.ts
	- ticket-detail.component.ts
	- 
- ticket-search
	- ticket-search.component.css
	- ticket-search.component.html
	- ticket-search.component.spec.ts
	- ticket-search.component.ts
	- 
- user
	- user.component.css
	- user.component.html
	- user.component.spec.ts
	- user.component.ts
   		- user-detail
			- user-detail.component.css
			- user-detail.component.html
			- user-detail.component.spec.ts
			- user-detail.component.ts
           
- assets
	- .gitkeep
       
- environments
        - environment.prod.ts
        - environment.ts


## Felhasználói dokumentáció

### Telepítés

- Látogasson el a https://github.com/tiborelone/alk_fejl_beadando/ oldalra.
- Itt kattintson a "Clone and Download" gombra, és azon belül kattintson a "Download as Zip" gombra.
- A letöltött állományt csomagolja ki.
- npm i parancsot adjuk ki parancssorban a kicsomagolt állomány mappájában.
- npm start-al elindíthatjuk a programot.

### A telepítéshez szükséges:

- NodeJS és npm, ami innen letölthető: npmjs.com/get-npm
- Internet elérés

### Használata
- Böngészőben a keresősávba írjuk be, hogy: localhost:4200
- Használhatjuk a programot.
    - Bizonyos funkciók használatához bejelentkezés szükséges










  
