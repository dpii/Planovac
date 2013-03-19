--Určí počet uživatelů, kteří mají v daný čas již vytvořenou událost v rámci skupiny


select count(*) from (select id_uzivatele from udalosti where zacatek > '10/10/2009' and konec < '10/10/2010') where    ;


select idUzivatele 

--Vypočte průměrný počet přípomínek uživatelů.


--Určí počet připomínek na danou událost


--Zjistí počet vedoucích skupin


--Vyhledá nejvyýše čtyři posledni události pro každého uživatele.