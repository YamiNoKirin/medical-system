# Medical System
Prototype for a medical system in Java

## Architecture

#### Services

- Add person to system
- Print emitted prescriptions
- Print sick/healthy/deceased people
- Print civilians/doctors
- Print institution staff
- Print institution patients
- Print institutions/hospitals/pharmacies
- Print healthy/sick/deceased doctors/patients

#### Actions
- Create person
- Create institution
- Remove person
- Remove institution
- Issue prescription
- Consult patient (DOC)
- Redeem prescription (PERS)
- Get sick (PERS)
- Get healed (PERS)
- Die (PERS)

#### Classes
- Medical management authority (singleton)
- Person
- Doctor (person)
- Civilian (person)
- Prescription
- Institution
- Hospital (institution)

## Info

Fiecare student va lucra la un proiect individual. Proiectul este structurat în mai multe etape. 
Conditia de punctare a proiectelor este aceea ca acestea sa nu prezinte erori de compilare si sa implementeze cerintele date. 
Codul aplicatiei va fi transmis pe e-mail pana la termenul de predare.

Terme de predare:

Etapa I: (01.04.2019, discutie in laborator - 02.04.2019/09.04.2019)

Etapa II: (05.05.2019, discutie in laborator - 07.05.2019/12.05.2019)

Etapa III: (Penultima si ultima saptamana din semestru)

Pentru orice informatie suplimentara sau neclaritati, 
discutam la laborator sau probleme punctuale bogdan.pahontu@endava.com.


Etapa I:
1. Definirea sistemului: 
	- sa se creeze o lista pe baza temei alese cu cel putin 10 actiuni/interogari care se pot face în cadrul sistemului 
  si o lista cu cel putin 8 tipuri de obiecte. 
2. Implementare: sa se implementeze în limbajul Java o aplicatie pe baza celor definite la punctul 1. Aplicatia va contine: 
	- clase simple cu atribute private / protected si metode de acces
	- cel putin doua colectii diferite capabile sa gestioneze obiectele definite anterior (List, Set, Map, etc.) 
  dintre care cel putin una sa fie sortata. (cerinta aceasta ramane pentru etapa a II a)
	- utilizare mostenire pentru crearea de clase aditionale si utilizarea lor în cadrul colectiilor;
	- cel putin o clasa serviciu care sa expuna operatiile 
	- o clasa main din care sunt facute apeluri catre servicii 
		
	
Etapa II:
1. Extindeti proiectul din prima etapa prin realizarea persistentei utilizând fisiere.
se vor realiza fisiere de tip csv pentru cel putin 4 dintre clasele definite in prima etapa
	- se vor realiza servicii generice pentru scrierea si citirea din fi?iere
	- la pornirea programului se vor încarca datele din fisiere utilizând serviciile
2. Realizarea unui serviciu de audit
	- se va realiza un serviciu care sa scrie într-un fisier de tip CSV de fiecare data când este executata una 
  dintre acsiunile descrise in prima etapa. Structura fisierului: timestamp actiune - Logger


Etapa III:
1. Înlocuiti serviciile realizate în etapa a II-a cu servicii care sa asigure persistenta utilizând baza de date folosind JDBC.
	- sa se realizeze servicii care sa expuna operatii de tip create, read, update, delete (CRUD)pentru cel putin 4 
  dintre clasele definite
	- se va implementa o functionalitate multithreading; 
  
2. Sa se realizeze o interfata grafica în care sa fie expuse cel putin 5 dintre actiunile definite initial. 
Interfata va avea cel putin 2 ecrane diferite care sa permita navigarea intre ele. 
Se va utiliza Swing sau JSP pentru realizarea interfetei grafice. 		
Utilizarea altor framework-uri în afara celor mentionate mai sus trebuie discutata în prealabil cu mine.
