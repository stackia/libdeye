============
Contributing
============

Welcome to ``libdeye`` contributor's guide.

This document focuses on getting any potential contributor familiarized
with the development processes, but `other kinds of contributions`_ are also
appreciated.

If you are new to using git_ or have never collaborated in a project previously,
please have a look at `contribution-guide.org`_. Other resources are also
listed in the excellent `guide created by FreeCodeCamp`_ [#contrib1]_.

Please notice, all users and contributors are expected to be **open,
considerate, reasonable, and respectful**. When in doubt, `Python Software
Foundation's Code of Conduct`_ is a good reference in terms of behavior
guidelines.


Issue Reports
=============

If you experience bugs or general issues with ``libdeye``, please have a look
on the `issue tracker`_. If you don't see anything useful there, please feel
free to fire an issue report.

.. tip::
   Please don't forget to include the closed issues in your search.
   Sometimes a solution was already reported, and the problem is considered
   **solved**.

New issue reports should include information about your programming environment
(e.g., operating system, Python version) and steps to reproduce the problem.
Please try also to simplify the reproduction steps to a very minimal example
that still illustrates the problem you are facing. By removing other factors,
you help us to identify the root cause of the issue.


Documentation Improvements
==========================

You can help improve ``libdeye`` docs by making them more readable and coherent, or
by adding missing information and correcting mistakes.

``libdeye`` documentation uses Sphinx_ as its main documentation compiler.
This means that the docs are kept in the same repository as the project code, and
that any documentation update is done in the same way was a code contribution.

.. tip::
    Please notice that the `GitHub web interface`_ provides a quick way of
    propose changes in ``libdeye``'s files. While this mechanism can
    be tricky for normal code contributions, it works perfectly fine for
    contributing to the docs, and can be quite handy.

    If you are interested in trying this method out, please navigate to
    the ``docs`` folder in the source repository_, find which file you
    would like to propose changes and click in the little pencil icon at the
    top, to open `GitHub's code editor`_. Once you finish editing the file,
    please write a message in the form at the bottom of the page describing
    which changes have you made and what are the motivations behind them and
    submit your proposal.

When working on documentation changes in your local machine, you can
compile them using |uv|_::

    uv run --group docs sphinx-build -b html docs docs/_build/html

and use Python's built-in web server for a preview in your web browser
(``http://localhost:8000``)::

    python3 -m http.server --directory 'docs/_build/html'


Code Contributions
==================

Submit an issue
---------------

Before you work on any non-trivial code contribution it's best to first create
a report in the `issue tracker`_ to start a discussion on the subject.
This often provides additional considerations and avoids unnecessary work.

Clone the repository
--------------------

#. Create an user account on |the repository service| if you do not already have one.
#. Fork the project repository_: click on the *Fork* button near the top of the
   page. This creates a copy of the code under your account on |the repository service|.
#. Clone this copy to your local disk::

    git clone git@github.com:stackia/libdeye.git
    cd libdeye

Create an environment
---------------------

This project uses |uv|_ to manage Python versions, a project virtual
environment, and locked dependencies. Install uv from
https://docs.astral.sh/uv/getting-started/installation/, then from the
repository root run::

    uv sync

``uv sync`` creates ``.venv``, installs the project in editable mode, and
installs the default ``dev`` dependency group from ``uv.lock``.
After that, ``uv run python`` can import the package under development.

Setup |pre-commit|_::

    uv run pre-commit install

``libdeye`` comes with a lot of hooks configured to automatically help the
developer to check the code being written.

Implement your changes
----------------------

#. Create a branch to hold your changes::

    git checkout -b my-feature

   and start making changes. Never work on the main branch!

#. Start your work on this branch. Don't forget to add docstrings_ to new
   functions, modules and classes, especially if they are part of public APIs.

#. Add yourself to the list of contributors in ``AUTHORS.rst``.

#. When you’re done editing, do::

    git add <MODIFIED FILES>
    git commit

   to record your changes in git_.

   Please make sure to see the validation messages from |pre-commit|_ and fix
   any eventual issues.
   This should automatically use flake8_/black_ to check/fix the code style
   in a way that is compatible with the project.

   .. important:: Don't forget to add unit tests and documentation in case your
      contribution adds an additional feature and is not just a bugfix.

      Moreover, writing a `descriptive commit message`_ is highly recommended.
      In case of doubt, you can check the commit history with::

         git log --graph --decorate --pretty=oneline --abbrev-commit --all

      to look for recurring communication patterns.

#. Please check that your changes don't break any unit tests with::

    uv run pytest

   Other common tasks:

   * Type check: ``uv run mypy .``
   * Build docs: ``uv run --group docs sphinx-build -b html docs docs/_build/html``
   * Build distributions: ``uv build``

Submit your contribution
------------------------

#. If everything works fine, push your local branch to |the repository service| with::

    git push -u origin my-feature

#. Go to the web page of your fork and click |contribute button|
   to send your changes for review.

Find more detailed information in `creating a PR`_. You might also want to open
the PR as a draft first and mark it as ready for review after the feedbacks
from the continuous integration (CI) system or any required fixes.


Troubleshooting
---------------

The following tips can be used when facing problems to build or test the
package:

#. Make sure to fetch all the tags from the upstream repository_.
   The command ``git describe --abbrev=0 --tags`` should return the version you
   are expecting. If you are trying to run CI scripts in a fork repository,
   make sure to push all the tags.
   Recreate the environment with ``uv sync --reinstall`` if the local
   checkout looks stale.

#. If a newly added dependency is missing after editing ``pyproject.toml``,
   refresh the lockfile and environment::

    uv lock
    uv sync

   To rebuild documentation after changing the ``docs`` group::

    uv sync --group docs
    uv run --group docs sphinx-build -b html docs docs/_build/html

#. Make sure uv is using the Python version pinned in ``.python-version``
   (currently 3.13). When in doubt you can run::

    uv python pin
    uv run python --version

#. `Pytest can drop you`_ in an interactive session in the case an error occurs.
   In order to do that you need to pass a ``--pdb`` option (for example by
   running ``uv run pytest -k <NAME OF THE FALLING TEST> --pdb``).
   You can also setup breakpoints manually instead of using the ``--pdb`` option.


Maintainer tasks
================

Releases
--------

   If instead you are using a different/private package index, please update
   the instructions accordingly.

If you are part of the group of maintainers and have correct user permissions
on PyPI_, the following steps can be used to release a new version for
``libdeye``:

#. Make sure all unit tests are successful.
#. Tag the current commit on the main branch with a release tag, e.g., ``v1.2.3``.
#. Push the new tag to the upstream repository_, e.g., ``git push upstream v1.2.3``
#. Clean up the ``dist`` and ``build`` folders (``rm -rf dist build``)
   to avoid confusion with old builds and Sphinx docs.
#. Run ``uv build --no-sources`` and check that the files in ``dist`` have
   the correct version (no ``.dirty`` or git_ hash) according to the git_ tag.
   Also check the sizes of the distributions, if they are too big (e.g., >
   500KB), unwanted clutter may have been accidentally included.
#. Run ``uv publish`` and check that everything was
   uploaded to PyPI_ correctly.



.. [#contrib1] Even though, these resources focus on open source projects and
   communities, the general ideas behind collaborating with other developers
   to collectively create software are general and can be applied to all sorts
   of environments, including private companies and proprietary code bases.


.. <-- start -->
.. |the repository service| replace:: GitHub
.. |contribute button| replace:: "Create pull request"

.. _repository: https://github.com/stackia/libdeye
.. _issue tracker: https://github.com/stackia/libdeye/issues
.. <-- end -->


.. |pre-commit| replace:: ``pre-commit``
.. |uv| replace:: ``uv``


.. _black: https://pypi.org/project/black/
.. _contribution-guide.org: https://www.contribution-guide.org/
.. _creating a PR: https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request
.. _descriptive commit message: https://chris.beams.io/posts/git-commit
.. _docstrings: https://www.sphinx-doc.org/en/master/usage/extensions/napoleon.html
.. _flake8: https://flake8.pycqa.org/en/stable/
.. _git: https://git-scm.com
.. _guide created by FreeCodeCamp: https://github.com/FreeCodeCamp/how-to-contribute-to-open-source
.. _other kinds of contributions: https://opensource.guide/how-to-contribute
.. _pre-commit: https://pre-commit.com/
.. _PyPI: https://pypi.org/
.. _Pytest can drop you: https://docs.pytest.org/en/stable/how-to/failures.html#using-python-library-pdb-with-pytest
.. _Python Software Foundation's Code of Conduct: https://www.python.org/psf/conduct/
.. _Sphinx: https://www.sphinx-doc.org/en/master/
.. _uv: https://docs.astral.sh/uv/

.. _GitHub web interface: https://docs.github.com/en/repositories/working-with-files/managing-files/editing-files
.. _GitHub's code editor: https://docs.github.com/en/repositories/working-with-files/managing-files/editing-files
