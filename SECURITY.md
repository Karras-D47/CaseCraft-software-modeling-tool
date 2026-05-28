# Security Policy

## Supported Versions

CaseCraft is an academic / portfolio project. Security fixes are applied only to
the latest version on the default branch.

| Version        | Supported          |
| -------------- | ------------------ |
| Latest (`main`)| :white_check_mark: |
| Older commits  | :x:                |

## Reporting a Vulnerability

If you discover a security vulnerability, please **do not open a public issue**.
Public disclosure before a fix is in place can put users at risk.

Instead, report it privately:

1. Go to the **Security** tab of this repository and use
   **"Report a vulnerability"** (GitHub Private Vulnerability Reporting), **or**
2. Contact the maintainers directly through their GitHub profiles:
   [k3rneluser](https://github.com/k3rneluser).

Please include, where possible:

- A clear description of the issue and its potential impact
- Steps to reproduce (a proof of concept, affected endpoint, or sample input)
- The affected component (e.g. authentication, project access, diagram generation)
- Any suggested remediation

### What to expect

- We aim to acknowledge a report within **7 days**.
- Once confirmed, we will work on a fix and keep you informed of progress.
- This is a volunteer-maintained educational project, so timelines are
  best-effort rather than guaranteed.

## Scope

The following are in scope:

- Authentication and session handling (login, registration, logout)
- Authorization / data isolation between users and their projects
- Injection vulnerabilities (SQL, template, etc.)
- Exposure of sensitive configuration or credentials

The following are **out of scope**:

- Issues that require physical access to a user's machine
- Vulnerabilities in third-party dependencies that have no available patch
  (please report these upstream)
- Denial of service through unrealistic traffic volumes against a local
  development instance

## A note on configuration

CaseCraft reads its database credentials from environment variables. Never
commit real credentials (database passwords, secrets) to the repository. If a
credential is ever exposed in the git history, rotate it immediately.
