<%-- 
    Document   : rencana_Ringkasan_LampiranA
    Created on : Feb 14, 2011, 12:30:30 PM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
</script>


<s:form beanclass="etanah.view.stripes.pembangunan.RencanaRingkasanLampiranAActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="70%" align="center">
                    <tr>
                        <td align="right"><b>LAMPIRAN 'A'</b></td>
                    </tr>
                </table>

                <table border="0" width="85%" align="center" cellspacing="10">
                    <tr>
                        <td align="left"><b> ${actionBean.permohonan.kodUrusan.nama} </b></td>
                    </tr>

                    <c:if test="${edit}">
                        <tr>
                            <td><s:textarea rows="5" cols="150" name="tujuan" class="normal_text"/></td>
                        </tr>
                    </c:if>
                    <c:if test="${edit2}">
                        <td>${actionBean.tujuan}</td>
                    </c:if>
                </table>

                <br/><br/>

                <table border="0" width="85%" align="center" cellspacing="10">
                    <tr>
                        <td>1) &nbsp; Permohonan di atas telah diluluskan :-   </td>
                    </tr>
                </table>

                <table border="0" width="80%" align="center" cellspacing="10">

                    <tr>
                        <td colspan="3">a) &nbsp; Syarat nyata asal dibatalkan dan digantikan dengan syarat-syarat seperti berikut :-   </td>
                    </tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TSPSS'}">
                        <tr>
                            <td colspan="3"> &nbsp;&nbsp;&nbsp;&nbsp; <b>${actionBean.hp.syaratNyataBaru.syarat} </b> </td>
                            <%--<td colspan="3"> &nbsp;&nbsp;&nbsp;&nbsp; <b>${actionBean.hp.hakmilik.syaratNyata.syarat} </b> </td> --%>
                        </tr>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TSKKT'}">
                            <tr>
                                <td width="20%">b) &nbsp; Penjenisan dikenakan    </td>
                                <td width="2%"><b>: </b></td>
                                <%--<td><b> ${actionBean.hp.kategoriTanahBaru.nama} </b></td>--%>
                                <td><b> ${actionBean.hp.hakmilik.kategoriTanah.nama} </b></td> 
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSKKT'}">
                            <tr>
                                <td width="20%">b) &nbsp; Penjenisan dikenakan    </td>
                                <td width="2%"><b>: </b></td>
                                <td><b> ${actionBean.hp.kategoriTanahBaru.nama} </b></td>
                                <%--<td><b> ${actionBean.hp.hakmilik.kategoriTanah.nama} </b></td> --%>
                            </tr>
                        </c:if>
                        <tr>
                            <td>c) &nbsp; Sekatan kepentingan    </td>
                            <td><b>: </b></td>
                            <%--<td><b> ${actionBean.hp.sekatanKepentinganBaru.sekatan} </b></td>--%>
                            <td><b> ${actionBean.hp.hakmilik.sekatanKepentingan.sekatan} </b></td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                        <tr>
                            <td colspan="3"> &nbsp;&nbsp;&nbsp;&nbsp; <b>${actionBean.hp.hakmilik.lot.nama} <fmt:formatNumber  pattern="00" value="${actionBean.hp.hakmilik.noLot}"/>  ${actionBean.hp.hakmilik.kodHakmilik.kod} <fmt:formatNumber  pattern="00" value="${actionBean.hp.hakmilik.noHakmilik}"/></b> </td>
                        </tr>
                        <tr>
                            <td colspan="3"> &nbsp;&nbsp;&nbsp;&nbsp;
                                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                                               requestURI="/pembangunan/melaka/rencanaRingkasanLampiranA" id="line">
                                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%">${line.noPlot}</display:column>
                                    <display:column title="Syarat Nyata" style="vertical-align:baseline">${line.kodSyaratNyata.syarat}</display:column>
                                </display:table>
                            </td>
                        </tr>
                        <tr>
                            <td width="26%" valign="top">b) &nbsp;Penjenisan dikenakan </td>
                            <td width="2%" valign="top"><b>: </b></td>
                            <td><display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                                           requestURI="/pembangunan/melaka/rencanaRingkasanLampiranA" id="line">
                                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%">${line.noPlot}</display:column>
                                    <display:column title="Penjenisan" style="vertical-align:baseline">${line.kategoriTanah.nama}</display:column>
                                </display:table>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top">c) &nbsp; Sekatan kepentingan    </td>
                            <td valign="top"><b>: </b></td>
                            <td><display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                                           requestURI="/pembangunan/melaka/rencanaRingkasanLampiranA" id="line">
                                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%">${line.noPlot}</display:column>
                                    <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">
                                        <c:if test="${line.kodSekatanKepentingan.kod eq null}">Tiada</c:if>
                                        <c:if test="${line.kodSekatanKepentingan.kod ne null}">${line.kodSekatanKepentingan.sekatan}</c:if></display:column>
                                </display:table>
                            </td>
                        </tr>
                    </c:if>
                    <c:set var="i" value="0"/>
                    <c:forEach items="${actionBean.hpList}" var="line">
                        
                        <%-- PREMIUM --%>
                        <tr>
                            <c:if test="${edit}">
                                <td>d) &nbsp; Premium    </td>
                                <td><b>: </b> </td>
                                <td><s:textarea rows="3" cols="80" name="premiumDesc${i}" class="normal_text" value="${line.keteranganKadarPremium}" /></td>
                            </c:if>
                        </tr>
                        <tr>
                            <c:if test="${edit}">
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RM    </td>
                                <td><b>: </b> </td>
                                <td><s:text name="premium${i}" class="normal_text" size="20" value="${line.kadarPremium}" onkeyup="validateNumber(this,this.value);"/></td>
                            </c:if>
                        </tr>
                        <tr>
                            <c:if test="${edit2}">
                                <td>d) &nbsp; Premium    </td>
                                <td>${line.keteranganKadarPremium}</td>                         
                            </c:if>
                        </tr>
                         <tr>
                            <c:if test="${edit2}">
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RM    </td>                        
                                <td>${line.kadarPremium}</td>
                            </c:if>
                        </tr>


                        <%-- HASIL --%>
                        <tr>
                            <c:if test ="${edit}">    
                                <td>e) &nbsp; Hasil    </td>
                                <td><b>:  </b></td>                             
                                <td><s:textarea rows="3" cols="80" name="hasilDesc${i}" class="normal_text" value="${line.keteranganCukaiBaru}"/></td>
                            </c:if>
                        </tr>
                        <tr>
                            <c:if test ="${edit}">    
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RM    </td>
                                <td><b>:  </b></td>                             
                                <td><s:text name="hasil${i}" class="normal_text" size="20" value="${line.cukaiBaru}" onkeyup="validateNumber(this,this.value);"/></td>
                            </c:if>
                        </tr>
                        <tr>
                            <c:if test="${edit2}">
                                <td>e) &nbsp; Hasil    </td>
                                <td>${line.keteranganCukaiBaru}</td>                                                         
                            </c:if>
                        </tr>
                        <tr>
                            <c:if test="${edit2}">
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RM    </td>                       
                                <td>${line.cukaiBaru}</td>
                            </c:if>
                        </tr>


                        <%-- SUMBANGAN SALIRAN --%>
                        <tr>
                            <c:if test = "${edit}">
                                <td>f) &nbsp; Sumbangan Saliran    </td>
                                <td><b>:  </b></td>
                                <td><s:textarea rows="3" cols="80" name="sumbanganSaliranDesc${i}" class="normal_text" value="${line.keteranganInfra}"/></td>
                            </c:if>
                        </tr>
                        <tr>
                            <c:if test ="${edit}">    
                                <td>&nbsp;&nbsp;&nbsp;&nbsp; RM    </td>
                                <td><b>:  </b></td>                             
                                <td><s:text name="sumbanganSaliran${i}" class="normal_text" size="20" value="${line.kosInfra}" onkeyup="validateNumber(this,this.value);"/></td>
                            </c:if>
                        </tr>
                        <tr>
                            <c:if test="${edit2}">
                                <td>e) &nbsp; Sumbangan Saliran    </td>
                                <td>${line.keteranganInfra}</td>                                                       
                            </c:if>    
                        </tr>
                        <tr>
                            <c:if test="${edit2}">
                                <td>&nbsp;&nbsp;&nbsp;&nbsp; RM    </td>                         
                                <td>${line.kosInfra}</td>
                            </c:if>    
                        </tr>

                        <c:set var="i" value="${i+1}" />
                    </c:forEach>
                </table>
                <c:if test ="${edit}">
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>