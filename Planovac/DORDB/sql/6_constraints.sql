--2.1.1

alter table Udalost
add constraint cas_check check (zacatek < konec);

--2.1.2 

alter table Skupina modify idVedouciho not null;

--2.1.3

alter table Skupina group_pass_check check length(password) > 5;

--2.1.4

alter table Uzivatel user_pass_check check length(password) > 5;

--2.2.1 

CREATE OR REPLACE PROCEDURE kontrola_pripominky (id_pripominky in number,  cas_pred_udalosti in date, zacatek in date) AS
cas_pripominky in date;
zacatek_udalosti in date;
BEGIN
 SELECT p.cas_pred_udalosti INTO cas_pripominky FROM pripominky WHERE p.id_pripominky = id_pripominky;
 SELECT u.zacatek INTO zacatek_udalosti FROM udalosti WHERE 

 IF (cas_pripominky > zacatek_udalosti) then
  raise_application_error(-20000, 'Nelze přidat připomínku v čase po zahájení události!');
 END IF;
END;


CREATE OR REPLACE TRIGGER trig_kontrola_pripominky
BEFORE insert or update 
ON Pripominky
FOR EACH ROW
  BEGIN
    kontrola_pripominky(:new.id_pripominky,:new.cas_pred_udalosti,:new.zacatek);
  END;
  
--2.2.2 

CREATE OR REPLACE PROCEDURE kontrola_skupiny (id_skupiny in number) AS
pocet_clenu in number;
BEGIN
 SELECT DISTINCT count(*) INTO pocet_clenu FROM UzivatelSkupiny u WHERE u.idSkupiny = id_skupiny ;

 IF (pocet_clenu > 150) then
  raise_application_error(-20000, 'Skupina je omezena pro maximální počet 150 členů!');
 END IF;
END;

CREATE OR REPLACE TRIGGER trig_kontrola_skupiny
BEFORE insert or update 
ON Skupiny
FOR EACH ROW
  BEGIN
    kontrola_pripominky(:new.idSkupiny);
  END;
