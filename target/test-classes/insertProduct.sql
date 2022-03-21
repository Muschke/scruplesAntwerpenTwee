insert into producten (eigenschapId, kleurId, merkId, maatId, soort, beschrijving, aankoopprijs, status, gestolen, solden,
        consignatiebonId, gebruikerId)
        values ((select ideigenschap from eigenschappen where subeigenschap = 'korte broek'),
                (select idkleur from kleuren where kleur = 'oranje'),
                (select idmerk from merken where merk = 'Chanel'),
                (select idmaten from maten where maat = 'M'), 'HEREN', 'testbeschrijving', 10, 'TEKOOP',0, 1,
        (select idConsignatiebon from consignatieBonnen where gebruikerId= (select idgebruiker from gebruikers where naam = 'testNaam')),
        (select idgebruiker from gebruikers where naam = 'testNaam'));






