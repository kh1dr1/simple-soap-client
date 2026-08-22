<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns2="http://eif.viko.lt/dealership-service/schema"
                exclude-result-prefixes="root deal">

    <xsl:output method="html" encoding="utf-8" indent="yes"/>

    <!-- XML DOM Root -->
    <xsl:template match="/">
        <xsl:apply-templates select="/ns2:dealership"/>
    </xsl:template>

    <!--
        Note: Namespace prefix (i.e. "ns2") *IS* needed here.
        The prefix is needed for elements coming from XSD Car POJO, but not
        my XsltDealership POJO.
    -->
    <xsl:template match="ns2:dealership">

        <!-- Note: Insert DOCTYPE declaration into HTML -->
        <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;</xsl:text>
        <html>
            <head>
                <title>Dealership: <xsl:value-of select="name"/></title>
            </head>
            <body>
                <h1><xsl:value-of select="name"/></h1>
                <p><strong>ID:</strong> <xsl:value-of select="id"/></p>
                <p><strong>Location:</strong> <xsl:value-of select="location"/></p>
                <p><strong>Phone:</strong> <xsl:value-of select="phone"/></p>

                <h2>Inventory</h2>
                <table border="1">
                    <tr>
                        <th>Brand</th>
                        <th>Model</th>
                        <th>Year</th>
                    </tr>

                    <!-- Note: Namespace prefix not needed -->
                    <xsl:for-each select="inventory">
                        <tr>
                            <!-- Note: Namespace prefix IS needed here -->
                            <td><xsl:value-of select="ns2:brand"/></td>
                            <td><xsl:value-of select="ns2:model"/></td>
                            <td><xsl:value-of select="ns2:year"/></td>
                        </tr>
                    </xsl:for-each>

                </table>

                <a href="/">Back to Main</a>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>