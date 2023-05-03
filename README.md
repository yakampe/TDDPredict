# Test-Driven Development (TDD) Enhancement Plugin for IntelliJ

![TDDPredict](https://cdn.discordapp.com/attachments/1077304012329721856/1103153885025140807/TDDPredict.gif)

The TDD Enhancement Plugin is designed to make Test-Driven Development more enjoyable and gamified, especially when doing katas or general development. This plugin streamlines the TDD workflow and allows you to easily track your test predictions and history.

## Features

- **Simple TDD Workflow**: Quickly press one of two prediction buttons - 'PASS' or 'FAIL' - to trigger the run configuration and perform the test.
- **Prediction Tracking**: The plugin records the outcome of the test and your prediction, allowing you to keep track of your correct and incorrect predictions, helping you focus on each step.
- **History Tracking**: The plugin maintains a history of your predictions per project, allowing you to review and learn from past mistakes or successes.
- **Multi-Language Support**: The plugin has been tested and confirmed to work with Java and Python test runners, making it versatile for various development environments.

## Installation

To install this plugin, follow these steps:

1. Open IntelliJ IDEA or Android Studio.
2. Go to `File > Settings > Plugins`.
3. Click on the `Marketplace` tab.
4. Search for `TDD Predict` plugin by name or keyword.
5. Click on the `Install` button next to the plugin.

## Usage

1. Access the plugin in your IDE by finding the TDD Predict Tool Window and expanding it (by default it is at the bottom).
2. When pressing the 'PASS' or 'FAIL' button, the plugin will execute the selected run configuration only.
2. Configure your test runner and any necessary settings to ensure tests are run correctly.
3. Press one of the prediction buttons ('PASS' or 'FAIL') to trigger the run configuration and perform the test.
4. View your prediction and history data to track your progress and learn from past mistakes or successes.

You can delete the data in the settings panel.

## FAQ

### Q: The View Commit button doesn't do anything?

A: To View commits you need to have a git remote configured.:

### Q: How do I delete existing data?

A: You can do this through the settings. The button is located within the tool window.:

## Contributing

If you'd like to contribute to the development of this plugin, please [create a new issue](https://github.com/yakampe/TDDPredict/issues/new) or submit a pull request on the [GitHub repository](https://github.com/yakampe/TDDPredict).

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/yaniskampe)


## License

This project is licensed under the [MIT License](LICENSE.md) - see the [LICENSE.md](LICENSE.md) file for details.
