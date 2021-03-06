<p align="center">
    <img src=".github/readme/logo.svg" height="125"/>
    <br />
</p>

<h1 align="center">
    TestAxis: Backend Application
</h1>

<p align="center">
    <a href="https://github.com/testaxis/testaxis-backend/actions?query=workflow%3ABuild">
        <img src="https://img.shields.io/github/workflow/status/testaxis/testaxis-backend/Build?style=for-the-badge" />
    </a>
    <a href="https://github.com/testaxis/testaxis-backend/actions?query=workflow%3ADeploy">
        <img src="https://img.shields.io/github/workflow/status/testaxis/testaxis-backend/Deploy?label=Deploy&style=for-the-badge" />
    </a>
    <a href="https://documenter.getpostman.com/view/14162304/TVzSjGs1">
        <img src="https://img.shields.io/badge/Documentation-API-brightgreen?style=for-the-badge" />
    </a>
</p>

The backend application of TestAxis gathers test execution reports, performs analyses, and provides an API for the IDE plugin.

## What is TestAxis?

Commonly used CI platforms such as GitHub Actions or Travis CI let developers dive through hundreds of lines of logs to find the issue of a failing build.
It turns out that the most important reason for a failing build is failing tests.
Developers can be helped by showing a detailed overview of the tests that failed after a CI run that does not require looking at log files.
The names and error messages of the failing tests are shown in this overview to obviate the need to look at log files.

However, even when the failing test is found, it is sometimes hard to identify the exact reason for the failure based on the test name/error message without additional context.
This could be because the reason for failure is not necessarily related to the concept under test which is reflected in the test name, which could especially be the case since during local development a developer might only run tests that are obviously related to the code change, whereas during a CI build a test with a less obvious connection might fail.
The error message might be too imprecise to judge what is going wrong.
For example, a failure message like “Failed asserting that 24 is equal to 15” may not be informative enough for the developer to come up with the fix.

Thus, additional context is needed to find the issue causing the test to fail.
Therefore, developers could be helped by providing “test context” in the detailed overview of failed tests.
The test context may include additional information such as the commit that made the test fail, the test code, and the _changed_ code under test.
The goal is to give developers insights into failing tests after CI builds and to provide them with information that helps to resolve the issues causing the failing tests faster.
The insights are provided directly in the IDE, right there where the developer needs to fix the failing tests.
The information presented to the user is based on historical test executions with the goal to shift the axis from builds to tests.

## Documentation

The TestAxis backend features an API to retrieve projects, builds, and test case executions.
It also provides endpoint to upload XML test and coverage reports.
Check out the documentation via the button below.

<a href="https://documenter.getpostman.com/view/14162304/TVzSjGs1">
    <img src="https://img.shields.io/badge/Documentation-API-brightgreen?style=for-the-badge" />
</a>

## Development

This is a Spring Boot application that features a REST API and provides WebSocket connections.

Run the application:
```bash
$ gradlew bootRun
```

Run the tests and static analysis tools:
```
$ gradlew check
```

## Security

If you discover any security related issues, please email mail@casperboone.nl instead of using the issue tracker.

## Credits

- [Casper Boone](https://github.com/casperboone)
- [All Contributors](../../contributors)

## License

The MIT License (MIT). Please see [License File](LICENSE.md) for more information.
