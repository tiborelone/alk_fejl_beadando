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


















  
