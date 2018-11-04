<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">
    <xsl:template match="/">
        <table>
            <tr>
                <th>Medication Name</th>
                <th>Way of usage</th>
                <th>Start of treatment</th>
                <th>End of treatment</th>
            </tr>
            <xsl:for-each select="patients/patient/treatments/treatment">
                <tr>
                    <xsl:choose>
                        <xsl:when test="contains(startDate, '2018') or contains(endDate, '2018')">
                            <td>
                                <xsl:value-of select="medication/medName"/>
                            </td>
                            <td>
                                <xsl:value-of select="medication/wayOfUsage"/>
                            </td>
                            <td>
                                <xsl:value-of select="startDate"/>
                            </td>
                            <td>
                                <xsl:value-of select="endDate"/>
                            </td>
                        </xsl:when>
                    </xsl:choose>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>
