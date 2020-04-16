import react.dom.b
import kotlin.js.Date

object Salomon {
    val cv = cv(
            name = "Salomon BRYS",
            title = T(
                    "en" to "CTO / mobile architect",
                    "fr" to "CTO / architecte mobile"
            ),
            email = "salomon@kodein.net",
            phone = T(
                    "en" to "+33 6 83 54 55 96",
                    "fr" to "06 83 54 55 96"
            ),
            birth = Date("1986-12-15")
    ) {
        section(
                title = T(
                        "en" to "Professional experience",
                        "fr" to "Expérience professionnelle"
                ),
                link = "http://linkedin.com/in/salomonbrys"
        ) {
            yearSpanList {
                (2018..Int.MAX_VALUE) (
                        "en" to {
                            title("Founder & CTO of Kodein Koders", "(multi-platform consultancy)")
                            description(
                                    "Creation of Kotlin multi-platform training, services, and open source tools.",
                                    "Certified Jetbrains partner & Kotlin trainer."
                            )
                        },
                        "fr" to {
                            title("Fondateur & CTO de Kodein Koders", "(Consulting multi-platformes)")
                            description(
                                    "Création de formations, services et outils open source Kotlin multi-platformes.",
                                    "Certifié partenaire Jetbrains & formateur Kotlin."
                            )
                        }
                )
                (2014..2017) (
                        "en" to {
                            title("Software and R&D Architect at Dental-Monitoring", "(dental Tracking)")
                            description(
                                    "Lead of the \"Industrial Software\" and \"Android Application\" teams.",
                                    "Kotlin and C++ architect (internal server and software, mobile application)."
                            )
                        },
                        "fr" to {
                            title("Architecte logiciel et R&D à Dental-Monitoring", "(tracking dentaire)")
                            description(
                                    "Recrutement et lead des équipes « Logiciel Industriel » et « Application Android ».",
                                    "Architecte Kotlin et C++ (serveur et logiciels internes, application mobile)."
                            )
                        }
                )
                (2013..2014) (
                        "en" to {
                            title("Architect & mobile lead dev at Weemo", "(video conferencing)")
                            description(
                                    "Lead of the mobile team in charge of iOS and Android native development.",
                                    "Java and C++ mobile SDK architecture & development."
                            )
                        },
                        "fr" to {
                            title("Architecte mobile à Weemo", "(vidéo conférence)")
                            description(
                                    "Lead de l’équipe mobile en charge du dévelopement natif iOS et Android.\n",
                                    "Architecture et dévelopement du SDK mobile Java et C++."
                            )
                        }
                )
                (2011..2013) (
                        "en" to {
                            title("Architect & mobile lead dev at Kick Your App", "(mobile digital agency)")
                            description(
                                    "Solution architect & lead of the mobile (Android & iOS) developer team."
                            )
                        },
                        "fr" to {
                            title("Architecte et lead mobile à Kick Your App", "(agence digitale mobile)")
                            description(
                                    "Archtecte solutions et lead de l'équipe de dévelopeurs mobile (Android & iOS)."
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
                (2010..2011) (
                        "en" to {
                            title("Research at Univerity of Kent", "(Canterbury, GB): concurrent languages")
                        },
                        "fr" to {
                            title("Recherche à Univerity of Kent", "(Canterbury, GB) : langages concurrents")
                        }
                )
                (2005..2010) (
                        "en" to {
                            title("Epitech Master", "of information technology at Epitech Paris")
                        },
                        "fr" to {
                            title("Master Epitech", "en Technologies de l’Information à Epitech Paris")
                        }
                )
            }
        }
        section(
                title = T(
                        "all" to "Open Source"
                ),
                link = "http://github.com/salomonbrys"
        ) {
            titleList(48) {
                T("all" to "Kodein Framework") (
                        "en" to {
                            title("Kotlin/Multiplatform for iOS, Android, Desktop & Servers.")
                            description("2.2k ☆ Dependency Injection, embedded NoSQL, Model View Intent, etc.")
                        },
                        "fr" to {
                            title("Kotlin/Multiplatform pour iOS, Android, Desktop & Serveurs.")
                            description("2.2k ☆ Injection de Dépendance, NoSQL embarqué, Model View Intent, ...")
                        }
                )
                T("all" to "ANR-Watchdog") (
                        "en" to {
                            title("Debugging tool for Android (freeze detection)")
                            description("1.7k ☆ Used (among others) by the Snapchat & Spotify Android apps.")
                        },
                        "fr" to {
                            title("Outil de debugging pour Android (détection des freezes)")
                            description("1.7k ☆ Utilisé (entre autres) par les apps Snapchat & Spotify.")
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
                        "all" to {
                            b { + "TakingKt" }
                            +", Paris, France"
                        }
                )
                2019 (
                        "en" to {
                            b { + "KotlinConf" }
                            +", Copenhagen, Denmark"
                            +" & "
                            b { + "Kotlin/Everywhere" }
                            +", Paris, France"
                        },
                        "fr" to {
                            b { + "KotlinConf" }
                            +", Copenhague, Danemark"
                            +" & "
                            b { + "Kotlin/Everywhere" }
                            +", Paris, France"
                        }
                )
                2018 (
                        "en" to {
                            b { + "Mobilization" }
                            +", Lodz, Poland"
                        },
                        "fr" to {
                            b { + "Mobilization" }
                            +", Lodz, Pologne"
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
            keywordList(86,
                    T(
                            "en" to "Airplane Private Pilot Licence (PPL/A)",
                            "fr" to "Licence de Pilote Privé Avion (PPL/A)"
                    ),
                    T(
                            "en" to "Board game addict",
                            "fr" to "Passioné de Jeux de Sociétés"
                    ),
                    T(
                            "en" to "Practice Badminton & Ski",
                            "fr" to "Pratique le Badminton et le Ski"
                    ),
                    T(
                            "en" to "6 beat Rock dancer & teacher",
                            "fr" to "Danseur et prof de Rock à 6 temps"
                    )

            )
        }
    }
}
