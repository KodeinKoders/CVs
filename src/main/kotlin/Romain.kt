import kotlinx.css.b
import react.dom.b
import react.dom.p
import utils.Name
import kotlin.js.Date

object Romain {
    const val name: Name = "Romain BOISSELLE"

    val cv = cv(
            name = this.name,
            title = T(
                    "en" to "Kotlin Developer / Trainer",
                    "fr" to "Développeur / Formateur Kotlin"
            ),
            email = "romain@kodein.net",
            phone = T(
                    "en" to "+33 6 12 56 56 50",
                    "fr" to "06 12 56 56 50"
            ),
            birth = Date("1987-09-12")
    ) {
        section(
                title = T(
                        "en" to "Professional experience",
                        "fr" to "Expérience professionnelle"
                ),
                link = "http://linkedin.com/in/romainbsl"
        ) {
            yearSpanList {
                (2018..Int.MAX_VALUE) (
                        "en" to {
                            title("Founder at Kodein Koders","(Kotlin Developer & Trainer)")
                            description(
                                "Providing consultancy, training, and Open Source tools for Kotlin technologies.",
                                "Certified Jetbrains partner & Kotlin trainer."
                            )
                        },
                        "fr" to {
                            title("Fondateur de Kodein Koders","(Developpeur et Formateur Kotlin)")
                            description(
                                "Formation, consultance, et développement Open Source Kotlin.",
                                "Certifié partenaire Jetbrains & formateur Kotlin."
                            )
                        }
                )
                (2014..2018) (
                        "en" to {
                            title("Tech Lead at Group S", "(Human Resources)")
                            description(
                                "In charge of development process, recruiting / supervising developers.",
                                "Revamping existing monolith architecture to microservices."
                            )
                        },
                        "fr" to {
                            title("Lead technique chez Group S", "(Ressources Humaines)")
                            description(
                                "En charge des process de développement, recrutement / supervision des développeur.",
                                "Migration d'une application monolithe vers une architecture microservices."
                            )
                        }
                )
                (2009..2014) (
                        "en" to {
                            title("Java Software Developer at OET", "(Food industry)")
                            description(
                                "From zero to production enhancement, maintenance and support."
                            )
                        },
                        "fr" to {
                            title("Developpeur d'application Java chez OET", "(Industrie agroalimentaire)")
                            description(
                                "Étude et conception de nouvelles fonctionnalités, maintenance et support."
                            )
                        }
                )
            }
        }
        section(
                title = T(
                        "en" to "Education",
                        "fr" to "Études"
                ),
                endsWithSeparator = true
        ) {
            yearSpanList {
                (2009..2011) (
                        "en" to {
                            title("Master", "IT Management at Institut d'Informatique Apppliquée")
                        },
                        "fr" to {
                            title("Master", "Management IT à Institut d'Informatique Apppliquée")
                        }
                )
                (2006..2008) (
                        "en" to {
                            title("IT Associate Degree", "at Institut d'Informatique Apppliquée")
                        },
                        "fr" to {
                            title("BTS Informatique de gestion", "à Institut d'Informatique Apppliquée")
                        }
                )
            }
        }
        section(
                title = T(
                        "all" to "Open Source"
                ),
                link = "http://github.com/romainbsl"
        ) {
            titleList(40) {
                T("all" to "Kodein Framework") (
                        "en" to {
                            title("Kotlin/Multiplatform for iOS, Android, Desktop & Servers.")
                            description("2.5k ☆ Dependency Injection, embedded NoSQL, Logging, MVI, etc.")
                        },
                        "fr" to {
                            title("Kotlin/Multiplatform pour iOS, Android, Desktop & Serveurs.")
                            description("2.5k ☆ Injection de Dépendance, NoSQL embarqué, Logging, MVI, ...")
                        }
                )
            }
        }
        section(
                title = T(
                        "en" to "Speaker",
                        "fr" to "Conférencier"
                ),
                endsWithSeparator = true
        ) {
            yearList {
                2020 (
                        "en" to {
                            p {
                                title("TakingKt", ", Paris, France")
                                description("Modern backend with Kotlin.")
                            }
                            p {
                                title("EveryhwereEvent", ", Paris, France")
                                description("State of the Kotlin/Multiplatform ecosystem (nov 2020).")
                            }
                        },
                        "fr" to {
                            p {
                                title("TakingKt", ", Paris, France")
                                description("Cloud moderne en Kotlin !")
                            }
                            p {
                                title("EveryhwereEvent", ", Online")
                                description("État de l'écosystème de Kotlin/Multiplatform (nov 2020).")
                            }
                        }
                )
                2019 (
                        "en" to {
                            p {
                                title("KotlinConf", ", Copenhagen, Denmark")
                                description("Workshop: Go multi-platform with Kotlin.")
                            }
                            p {
                                title("Kotlin/Everywhere", ", Paris, France")
                                description("Workshop: Modern cloud application with Kotlin.")
                            }
                        },
                        "fr" to {
                            p {
                                title("KotlinConf", ", Copenhague, Danemark")
                                description("Workshop: Go multi-platform with Kotlin.")
                            }
                            p {
                                title("Kotlin/Everywhere", ", Paris, France")
                                description("Workshop: Modern cloud application with Kotlin.")
                            }
                        }
                )
            }
        }
        section(
                title = T(
                        "en" to "And also...",
                        "fr" to "Et aussi..."
                )
        ) {
            keywordList(75,
                    T(
                            "en" to "Zero Waste addict",
                            "fr" to "Adepte du Zéro déchet"
                    ),
                    T(
                            "en" to "Mountain lover",
                            "fr" to "Passionné par la montagne"
                    ),
                    T(
                            "en" to "Motorcycle mechanics",
                            "fr" to "Mécanqiue moto"
                    ),
                    T(
                            "en" to "Landscape / Hiking / Skiing",
                            "fr" to "Paysage / Randonnée / Ski"
                    )
            )

        }
    }
}