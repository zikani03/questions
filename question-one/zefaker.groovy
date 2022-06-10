generateFrom([
    "firstName": { faker -> faker.name().firstName() },
    "lastName": { faker -> faker.name().firstName() },
    "dateOfBirth": { faker -> "1990-01-01" },
    "phone": { faker -> "265888981234" },
])