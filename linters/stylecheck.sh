#!/usr/bin/env bash

run_ktlint()
{
    echo "--------------Running Ktlint--------------"
    ./gradlew ktlint
    RESULT="$?"

    if [ "$RESULT" = 0 ]
    then
        echo "Ktlint inspection ran successfully"
        return 0
    else
        echo "Ktlint has found issues that need to be resolved"
        return 1
    fi
}

run_detekt()
{
    echo "--------------Running Detekt--------------"
    ./gradlew detekt
    RESULT="$?"

    if [ "$RESULT" = 0 ]
    then
        echo "Detekt inspection ran successfully"
        return 0
    else
        echo "Detekt has found issues that need to be resolved"
        return 1
    fi
}

check_quality()
{
    ascii
    echo "--------------Launching code-style inspections--------------"
    local name
    name="run_ktlint"
    run_ktlint
    local ktlint_res=$?
    if [ "$ktlint_res" = 0 ]; then
        run_detekt
        local detekt_res=$?
        name="run_detekt"
        if [ "$detekt_res" = 0 ]; then
            echo "Code quality checks completed successfully!"
            exit 0
        else
            echo "Code quality checks have failed on step: $name"
            exit 1
        fi
    else
        echo "Code quality checks have failed on step: $name"
        exit 1
    fi
}

ascii()
{
    echo "  _______  _______  ______   _______      _______           _______          __________________             "
    echo " (  ____ \(  ___  )(  __  \ (  ____ \    (  ___  )|\     /|(  ___  )( \      \__   __/\__   __/|\     /|    "
    echo " | (    \/| (   ) || (  \  )| (    \/    | (   ) || )   ( || (   ) || (         ) (      ) (   ( \   / )    "
    echo " | |      | |   | || |   ) || (__        | |   | || |   | || (___) || |         | |      | |    \ (_) /     "
    echo " | |      | |   | || |   | ||  __)       | |   | || |   | ||  ___  || |         | |      | |     \   /      "
    echo " | |      | |   | || |   ) || (          | | /\| || |   | || (   ) || |         | |      | |      ) (       "
    echo " | (____/\| (___) || (__/  )| (____/\    | (_\ \ || (___) || )   ( || (____/\___) (___   | |      | |       "
    echo " (_______/(_______)(______/ (_______/    (____\/_)(_______)|/     \|(_______/\_______/   )_(      \_/       "
    echo "                                                                                                            "
}

cd ..
check_quality
