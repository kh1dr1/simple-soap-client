<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:root="http://your.namespace.com"
                xmlns:deal="http://viko.lt/dealership-service/schema"
                exclude-result-prefixes="root deal">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- ROOT -->
    <xsl:template match="/">
        <xsl:apply-templates select="/root:dealership"/>
    </xsl:template>

    <xsl:template match="root:dealership">
        <html>
            <head>
                <title>Dealership: <xsl:value-of select="deal:name"/></title>
            </head>
            <body>
                <h1><xsl:value-of select="deal:name"/></h1>
                <p><strong>ID:</strong> <xsl:value-of select="@id"/></p>
                <p><strong>Location:</strong> <xsl:value-of select="deal:location"/></p>
                <p><strong>Phone:</strong> <xsl:value-of select="deal:phone"/></p>

                <h2>Inventory</h2>
                <xsl:apply-templates select="deal:inventory"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="deal:inventory">
        <table border="1">
            <tr>
                <th>Brand</th>
                <th>Model</th>
                <th>Year</th>
            </tr>
            <xsl:for-each select="deal:car">
                <tr>
                    <td><xsl:value-of select="deal:brand"/></td>
                    <td><xsl:value-of select="deal:model"/></td>
                    <td><xsl:value-of select="deal:year"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>

</xsl:stylesheet>