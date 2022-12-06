<!-- PROJECT SHIELDS -->
<!--
* I'm using markdown "reference style" links for readability.
* Reference links are enclosed in brackets [ ] instead of parentheses ( ).
* See the bottom of this document for the declaration of the reference variables
* for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
* https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

![](https://img.shields.io/badge/Personal_Project-blue)

# Krypto

> Monitor various crypto assets and perform mock purchases to test your crypto skills from the safety and comfort of your phone.

## Technologies

> Native application developed using the **Kotlin** programming language.

Below are some of the key android concepts used/applied in this project:

- Clean Architecture
- Jetpack compose
- Feature multi-modularization
- Room Database
- Retrofit / OkHttp
- Dagger-Hilt
- Shared preferences
- etc

### Project Tasks
Each feature is divided according to 3 layers; presentation, domain, data 

- [ ] Home Feature (Presentation)
- [ ] Home Feature (Domain)
- [ ] Home Feature (Data)
- [ ] Transaction Feature (Presentation)
- [ ] Transaction Feature (Domain)
- [ ] Transaction Feature (Data)
- [ ] Coin List Feature (Presentation)
- [ ] Coin List Feature (Domain)
- [ ] Coin List Feature (Data)
- [ ] Coin Details Feature (Presentation)
- [ ] Coin Details Feature (Domain)
- [ ] Coin Details Feature (Data)
- [ ] Setting Feature (Presentation)
- [ ] Setting Feature (Domain)
- [ ] Setting Feature (Data)

## UI Designs

### Light
<img src="images/design_dark_splash.jpg" height="400" alt="Splash screen"/> <img src="images/design_light_home.jpg" height="400" alt="Home screen"/> <img src="images/design_light_coin_list.jpg" height="400" alt="Coin list screen"/>
<img src="images/design_light_coin_details.jpg" height="400" alt="Coin details screen"/> <img src="images/design_light_choose_asset.jpg" height="400" alt="Choose asset screen"/> <img src="images/design_light_buy_sell.jpg" height="400" alt="Buy - Sell screen"/>
<img src="images/design_light_settings.jpg" height="400" alt="Settings screen"/> <img src="images/design_light_app_info.jpg" height="400" alt="App info screen"/> <img src="images/design_light_loading_example.jpg" height="400" alt="Loading example screen"/>

### Dark
<img src="images/design_dark_splash.jpg" height="400" alt="Splash screen"/> <img src="images/design_dark_home.jpg" height="400" alt="Home screen"/> <img src="images/design_dark_coin_list.jpg" height="400" alt="Coin list screen"/>
<img src="images/design_dark_coin_details.jpg" height="400" alt="Coin details screen"/> <img src="images/design_dark_choose_asset.jpg" height="400" alt="Choose asset screen"/> <img src="images/design_dark_buy_sell.jpg" height="400" alt="Buy - Sell screen"/>
<img src="images/design_dark_settings.jpg" height="400" alt="Settings screen"/> <img src="images/design_dark_app_info.jpg" height="400" alt="App info screen"/> <img src="images/design_dark_loading_example.jpg" height="400" alt="Loading example screen"/>

## Getting Started

To get a local copy up and running follow these simple example steps.

```bash
git clone https://github.com/RyanKoech/android-krypto.git
```

### Prerequisites
Installed Android Studio or Any other android development IDEs and their respective dependencies.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! <br/>
You can pick a thing to do from the tasks list provided above and work on it.<br/>

Observe the following in order to have your PR merged successfully.
- Create branches from the `dev` branch
- Create a PR to the `dev` branch
- Prefix branch name with it purpose ie `feature`, `bugfix`, `hotfix`
- For feature branches use the following naming conventions `feature_<feature-name>_<layer ie domain/data/presentation>`

Feel free to check the [issues page](../../issues/) or [discussions page](../../discussions).

## Show your support

Give a ⭐ if you like this project!

## Acknowledgments

- [Brian Njogu](https://brayo.co/) - original ideator of Krypto and developer of the react native version

## 📝 License

This project is [MIT](./MIT.md) licensed.


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/RyanKoech/android-krypto.svg?style=for-the-badge
[contributors-url]: https://github.com/RyanKoech/android-krypto/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/RyanKoech/android-krypto.svg?style=for-the-badge
[forks-url]: https://github.com/RyanKoech/android-krypto/network/members
[stars-shield]: https://img.shields.io/github/stars/RyanKoech/android-krypto.svg?style=for-the-badge
[stars-url]: https://github.com/RyanKoech/android-krypto/stargazers
[issues-shield]: https://img.shields.io/github/issues/RyanKoech/android-krypto.svg?style=for-the-badge
[issues-url]: https://github.com/RyanKoech/android-krypto/issues
[license-shield]: https://img.shields.io/github/license/RyanKoech/android-krypto.svg?style=for-the-badge
[license-url]: https://github.com/RyanKoech/android-krypto/blob/master/LICENSE
