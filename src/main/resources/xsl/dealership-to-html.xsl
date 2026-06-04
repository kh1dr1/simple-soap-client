<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://your.dealership.namespace">  <!-- Add your actual namespace -->

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:strip-space elements="*"/>

    <!-- Root template -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Dealership: <xsl:value-of select="//name"/></title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    .dealership { border: 1px solid #ccc; padding: 20px; border-radius: 5px; }
                    .header { background-color: #f0f0f0; padding: 10px; margin-bottom: 20px; }
                    .info { margin: 10px 0; }
                    .label { font-weight: bold; display: inline-block; width: 100px; }
                    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
                    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
                    th { background-color: #4CAF50; color: white; }
                </style>
            </head>
            <body>
                <xsl:apply-templates select="//ns:Dealership"/>
            </body>
        </html>
    </xsl:template>

    <!-- Dealership template -->
    <xsl:template match="ns:Dealership">
        <div class="dealership">
            <div class="header">
                <h1><xsl:value-of select="name"/></h1>
            </div>

            <div class="info">
                <span class="label">Dealer ID:</span>
                <span><xsl:value-of select="@id"/></span>
            </div>

            <div class="info">
                <span class="label">Location:</span>
                <span><xsl:value-of select="location"/></span>
            </div>

            <div class="info">
                <span class="label">Phone:</span>
                <span><xsl:value-of select="phone"/></span>
            </div>

            <xsl:apply-templates select="inventory"/>
        </div>
    </xsl:template>

    <!-- Inventory template -->
    <xsl:template match="inventory">
        <h2>Inventory</h2>
        <xsl:apply-templates/>
    </xsl:template>

    <!-- Handle specific inventory items - adjust based on your actual structure -->
    <xsl:template match="vehicle">
        <div class="vehicle">
            <h3><xsl:value-of select="make"/> <xsl:value-of select="model"/></h3>
            <div>Year: <xsl:value-of select="year"/></div>
            <div>Price: $<xsl:value-of select="price"/></div>
        </div>
    </xsl:template>

</xsl:stylesheet>