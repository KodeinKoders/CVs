import react.dom.p
import utils.Name
import kotlin.js.Date

object Romain {
    const val name: Name = "Romain BOISSELLE"

    val cv = cv(
            name = this.name,
            title = T(
                "en" to "Mobile developer",
                "fr" to "Développeur mobile"
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
                (2018..Int.MAX_VALUE)(
                    "en" to {
                        title("Founder at Kodein Koders", "(Kotlin Developer & Trainer)")
                        description(
                            "Providing consultancy, training, and Open Source tools for Kotlin technologies.",
                            "Certified JetBrains partner & Kotlin trainer.",
                            "- Kotlin Multiplpatform development at and architecture at SumUp (POS).",
                            "... Kotlin & Swift, Coroutines, Redux & CQRS",
                            "- Kotlin Multiplpatform development at ACINQ, on a Lightning (Bitcoin) Wallet.",
                            "... Kotlin & Swift, Coroutines, Bitcoin protocol, Socket & Electrum watching",
                            "- Android development and architecture at Carrefour, on online shopping app.",
                            "... Kotlin, Coroutines, MVI, modularization & improving the codebase",
                            "- Android development at SFR, on a email app.",
                            "... Kotlin, Coroutines, setting up best practices",
                        )
                    },
                    "fr" to {
                        title("Fondateur de Kodein Koders", "(Developpeur et Formateur Kotlin)")
                        description(
                            "Formation, consultance, et développement Open Source Kotlin.",
                            "Certifié partenaire JetBrains & formateur Kotlin.",
                            "- Kotlin Multiplpatform et architecture chez SumUp, Point-Of-Sale.",
                            "... Kotlin & Swift, Coroutines, Redux & CQRS",
                            "- Kotlin Multiplpatform chez ACINQ, sur un portefeuille Lightning (Bitcoin).",
                            "... Kotlin & Swift, Coroutines, Bitcoin protocol, Socket & Electrum watching",
                            "- Android dev et architecture chez Carrefour, pour l'app de courses en ligne.",
                            "... Kotlin, Coroutines, MVI, modularisation & renforcement de l'existant.",
                            "- Android dev chez SFR, sur le client d'emailing.",
                            "... Kotlin, Coroutines, mise en place de l'architecture et bonnes pratiques",
                        )
                    }
                )
                (2014..2019)(
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
                    },
                    "fr" to {
                        title("Developpeur d'application Java chez OET", "(Industrie agroalimentaire)")
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
                (2006..2011)(
                    "en" to {
                        title("Master", "IT Management at Institut d'Informatique Apppliquée")
                    },
                    "fr" to {
                        title("Master", "Management IT à Institut d'Informatique Apppliquée")
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
                            title("Kotlin Multiplatform for iOS, Android, Desktop & Servers.")
                            description("2.9k ☆ Dependency Injection, embedded NoSQL, Logging, MVI, etc.")
                        },
                        "fr" to {
                            title("Kotlin Multiplatform pour iOS, Android, Desktop & Serveurs.")
                            description("2.9k ☆ Injection de Dépendance, NoSQL embarqué, Logging, MVI, ...")
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
                2022(
                    "en" to {
                        p {
                            title("FOSDEM", "Kotlin DevRoom")
                            description("Advanced dependency injection for Kotlin Multiplatform")
                        }
                    },
                    "fr" to {
                        p {
                            title("FOSDEM", "Kotlin DevRoom")
                            description("Injection de dépendance avancée pour Kotlin Multiplatform")
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