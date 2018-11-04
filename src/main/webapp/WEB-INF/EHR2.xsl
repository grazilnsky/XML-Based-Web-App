<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:strip-space elements="*"/>
    <xsl:output indent="yes"/>
    <xsl:template match = "/">
                <h2>Patients</h2>
                <table border = "1">
                    <tr>
                        <th>Name</th>
                        <th>Diagnostics</th>
                        <th>Treatments</th>
                        <th>Treatment Periods</th>
                        <th>Immunisation Date</th>
                        <th>Allergies</th>
                        <th>Radio Images</th>
                        <th>Test Results</th>
                        <th>Lab</th>
                        <th>Test Date</th>
                    </tr>
                    <xsl:for-each select="patients/patient">
                        <tr>
                            <xsl:choose>
                                <xsl:when test="radioImages/radioImage/path = ''">
                                    <td style="background-color:red"><xsl:value-of select="patientName" /></td>
                                </xsl:when>
                                <xsl:otherwise>
                                    <td ><xsl:value-of select="patientName" /></td>
                                </xsl:otherwise>
                         
                            </xsl:choose>
                            <td><xsl:for-each select="diagnostics/diagnostic"><xsl:value-of select="diagnosticName"/>. State: <xsl:value-of select="state"/><br></br></xsl:for-each></td>
                            <td><xsl:for-each select="treatments/treatment"><xsl:value-of select="medication/medName"/>. <xsl:value-of select="medication/wayOfUsage"/><br></br></xsl:for-each></td>
                            <td><xsl:for-each select="treatments/treatment"><xsl:value-of select="startDate"/> - <xsl:value-of select="endDate"/><br></br></xsl:for-each></td>
                            <td><xsl:value-of select="immunisationDate"/></td>
                            <td><xsl:for-each select="allergies/allergy"><xsl:value-of select="."/><br></br></xsl:for-each></td>
                            <td><img><xsl:attribute name="src"><xsl:value-of select="radioImages/radioImage/path"/></xsl:attribute></img></td>
                            <td><xsl:value-of select="testResults/details"/></td>
                            <td><xsl:value-of select="testResults/lab/labInfo"/></td>
                            <td><xsl:value-of select="testResults/testDate"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
    </xsl:template>
</xsl:stylesheet>