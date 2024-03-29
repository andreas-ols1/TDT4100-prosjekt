# TDT4100-prosjekt

## Beskrivelse av prosjektet

Vi skal lage en forenklet versjon av nettspillet Pokémon Showdown.

### Beskrivelse av appen

Pokémon Showdown er et trekksbasert nettspill som simulerer en Pokémon battle. Der har man lag på 6 pokémon, og utfører et move hvert trekk. I vår forenklede versjon av dette spillet skal vi ha lag på 3 pokémon. Formålet med spillet er å vinne over motstanderen din. Vi skal også lage en grunnleggende TeamBuilder som et tillegg til filbehandlingsdelen.

### Grunnklasser og brukergrensesnitt

Grunnklassene vi skal bruke i prosjektet vil være Pokémon, Type og Move. Hver pokémon vil ha et sett med atributter, blant annet hva slags type det er og et movesett. Det er foreløpig ikke bestemt på implementasjonen av Pokémon-klassen blir. Vi holder mulighetene åpne for å lage subklasser av Pokémon-klassen for å lettere implementere forskjellige Pokémon. Pokémon-klassen skal også ha attributter som skiller de forskjellige typene pokémon fra hverandre (stats, Type og Moves). Type-klassen skal ha attributter som bestemmer effektiviteten til ulike moves på visse pokémon.

### Filbehandling

I filbehandlingsdelen av prosjektet skal man kunne sette sammen et lag med forskjellige pokémon. Dette laget skal man kunne lagre til fil, og laste inn dersom man ikke ønsker et tilfeldig lag.

### Testing

Mye av funksjonaliteten i spillet baserer seg på matematikk. Spesielt angrepene bruker mye matematikk. Derfor vil en stor del av testene gå ut på å sjekke om kalkulasjonene er korrekte. I praksis vil alle metoder som ikke er getters og setters testes.
