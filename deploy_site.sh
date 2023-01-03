#!/bin/bash

mvn javadoc:javadoc
rm -Rf ~/public_html/javadoc/Utility-types
mv target/site ~/public_html/javadoc/Utility-types
