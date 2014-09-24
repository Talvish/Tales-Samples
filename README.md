Tales-Samples
=============

This repository contains a set of samples making use of the Tales framework and Tales-Rigs client and services. The recommended order for looking at the samples is:

<ol>
<li>Simple Service</li>
<li>Multiversion Service</li>
<li>User Service / Client (requires a running ObjectId Service from Tales - Rigs)</li>
<li>Complex Service</li>
<li>Website Service</li>
</ol>

Running the Samples
--------------------
Maven produces a fully working version of each service. The service, config and dependencies are all placed into the target directory. To run one of the samples the command line will look something like:

<code>java -jar sample_filename.jar -settings.file=config/settings.properties</code>

For example, to run the Complex Service you would type:

<code>java -jar tales.samples.complex_service-0.0.1-SNAPSHOT.jar -settings.file=config/settings.properties</code>


Related Repositories
--------------------

In total there are three repositories that make up the Tales suite:

* <b>Tales</b>: This is the primary repository that contains only the framework.<br>
https://github.com/Talvish/Tales

* <b>Tales - Samples</b>: This repository contains various samples show the capabilities of the framework. Samples range from simple, to complex, to near real services. <br>
https://github.com/Talvish/Tales-Samples

* <b>Tales - Rigs</b>: This repository contains usable services and their clients built using Tales. The intention is for these components to be used in real environments.<br>
https://github.com/Talvish/Tales-Rigs