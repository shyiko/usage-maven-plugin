#usage-maven-plugin#

Maven 3+ Plugin for printing project Usage information.

Usage
---------------

Add following snippet to your pom.xml (change &lt;usage/&gt; section) and hit "mvn"

    <build>
        <plugins>
            <plugin>
                <groupId>com.github.shyiko.usage-maven-plugin</groupId>
                <artifactId>usage-maven-plugin</artifactId>
                <version>1.0.0</version>
                <configuration>
                    <usage>
                        # deploy snapshot to Sonatype Nexus Snapshots
                        mvn clean deploy

                        # deploy release to Sonatype Nexus Staging
                        mvn clean release:clean release:prepare release:perform
                    </usage>
                </configuration>
            </plugin>
        </plugins>
        <extensions>
            <extension>
                <groupId>com.github.shyiko.usage-maven-plugin</groupId>
                <artifactId>usage-maven-plugin</artifactId>
                <version>1.0.0</version>
            </extension>
        </extensions>
    </build>

>NOTE: registration of usage-maven-plugin as extension is not strictly required, but it frees from enabling non-recursive mode (that is, by using -N flag) and explicit definition of &lt;defaultGoal&gt;usage:show&lt;/defaultGoal&gt;. So, it's usually as good thing.

Result is shown below:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![](http://i.minus.com/jbqyE1Al0ObBgf.png)

License
---------------

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)