<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:root="http://eif.viko.lt/dealership-service/schema"
                exclude-result-prefixes="root deal">

    <xsl:output method="html" encoding="utf-8" indent="yes"/>

    <!-- ROOT -->
    <xsl:template match="/">
        <xsl:apply-templates select="/root:dealership"/>
    </xsl:template>

    <xsl:template match="root:dealership">
        <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;</xsl:text>
        <html>
            <head>
                <title>Dealership: <xsl:value-of select="root:name"/></title>
            </head>
            <body>
                <h1><xsl:value-of select="root:name"/></h1>
                <p><strong>ID:</strong> <xsl:value-of select="root:id"/></p>
                <p><strong>Location:</strong> <xsl:value-of select="root:location"/></p>
                <p><strong>Phone:</strong> <xsl:value-of select="root:phone"/></p>

<!--                <h2>Inventory</h2>-->
<!--                <xsl:apply-templates select="deal:inventory"/>-->
            </body>
        </html>
    </xsl:template>

<!--    <xsl:template match="deal:inventory">-->
<!--        <table border="1">-->
<!--            <tr>-->
<!--                <th>Brand</th>-->
<!--                <th>Model</th>-->
<!--                <th>Year</th>-->
<!--            </tr>-->
<!--            <xsl:for-each select="deal:car">-->
<!--                <tr>-->
<!--                    <td><xsl:value-of select="deal:brand"/></td>-->
<!--                    <td><xsl:value-of select="deal:model"/></td>-->
<!--                    <td><xsl:value-of select="deal:year"/></td>-->
<!--                </tr>-->
<!--            </xsl:for-each>-->
<!--        </table>-->
<!--    </xsl:template>-->

</xsl:stylesheet>