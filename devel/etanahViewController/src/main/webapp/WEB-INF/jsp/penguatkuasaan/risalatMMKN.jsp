<%-- 
    Document   : risalatMMKN
    Created on : Jul 12, 2010, 11:10:49 AM
    Author     : Rohan
    Modified   : khairul.hazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<script type="text/javascript">
    function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if(rowcount < 4){
            var row = table.insertRow(rowcount);

            var leftcell = row.insertCell(0);
            var el = document.createElement('textarea');
            el.name = 'ulasan' + rowcount;
            el.rows = 5;
            el.cols = 150;
            el.style.textTransform = 'uppercase';
            leftcell.appendChild(el);
        } else{
            alert('Ulasan Pentadbir Tanah telah melebihi had yang dibenarkan.');
            $("#perakuanptg").focus();
            return true;
        }

    }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.RisalatMMKNActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <c:set scope="request" var="senaraiPemohon" value="${actionBean.listPemohon}"/>
            <div class="content">
                <table width="90%" border="0">
                    <tr><td><b>TAJUK : </b>&nbsp; ${actionBean.tajuk}</td></tr><br>     

                    <c:if test="${edit}">
                        <tr><td><b><font style="text-transform:uppercase">BIL. RISALAT MMKN  : </b>&nbsp; <s:text name="norisalat" size="30" class="normal_text"/></font></td></tr><br>
                                </c:if>
                                <c:if test="${!edit}">
                        <tr><td><b><font style="text-transform:capitalize">BIL. RISALAT MMKN  : </b>&nbsp; ${actionBean.norisalat} </font></td></tr><br>
                                </c:if>

                    <tr><td><b><font style="text-transform:capitalize">NO. RUJUKAN PTG  : </b> &nbsp; ${actionBean.permohonan.idPermohonan} &nbsp;</font></td></tr><br>
                </table>

                <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>
                <div align="center">
                    <table class="tablecloth" border="0" width="90%">
                        <tr><th colspan="2" rowspan="2"><b>RINGKASAN PERMOHONAN</b></th></tr><br><br/>
                        <tr>
                            <td><table border="0">                               
                                </table>
                            </td>
                        </tr>

                        <tr><td><b>Pemohon</b></td>
                            <td><table border="0" width="96%">
                                </table>
                                <display:table class="tablecloth" name="${actionBean.permohonan.senaraiPemohon}" cellpadding="0" cellspacing="0" id="line">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                                    <display:column title="Nama" style="vertical-align:baseline">
                                        ${line.pihak.nama}
                                    </display:column>

                                </display:table>
                            </td>
                        </tr>

                        <tr><td><b>Perihal Tanah </b></td>
                            <td><table border="0" width="50%">
                                    <tr><td>
                                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                                <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                                                    <c:if test="${tbl1.hakmilik ne null}">
                                                        ${tbl1.hakmilik.lot.nama} <fmt:formatNumber pattern="00" value="${tbl1.hakmilik.noLot}"/>
                                                    </c:if>
                                                    <c:if test="${tbl1.hakmilik eq null}">
                                                        ${tbl1.lot.nama} <fmt:formatNumber pattern="00" value="${tbl1.noLot}"/>
                                                    </c:if>

                                                </display:column>
                                                <display:column title="Luas" style="vertical-align:baseline">
                                                    <c:if test="${tbl1.hakmilik ne null}">
                                                        <fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}
                                                    </c:if>
                                                    <c:if test="${tbl1.hakmilik eq null}">
                                                        <fmt:formatNumber pattern="#,##0.0000" value="${tbl1.luasTerlibat}"/>&nbsp;${tbl1.kodUnitLuas.nama}
                                                    </c:if>
                                                </display:column>
                                                <%--<display:column title="Daerah" property="hakmilik.daerah.nama" class="daerah" style="vertical-align:baseline"/>--%>
                                                <display:column title="Mukim" style="vertical-align:baseline">
                                                    <c:if test="${tbl1.hakmilik ne null}">
                                                        ${tbl1.hakmilik.bandarPekanMukim.nama}
                                                    </c:if>
                                                    <c:if test="${tbl1.hakmilik eq null}">
                                                        ${tbl1.bandarPekanMukimBaru.nama}
                                                    </c:if>
                                                </display:column>
                                            </display:table>
                                        </td></tr>
                                </table>
                            </td>
                        </tr>

                        <tr><td><b>Lokasi</b></td>
                            <c:if test="${edit}">
                                <td><s:textarea name="lokasitanah" rows="5" cols="130" class="normal_text"/></td>
                            </c:if>
                            <c:if test="${!edit}">
                                <td>${actionBean.lokasitanah} &nbsp;</td>
                            </c:if>
                        </tr>                                    

                        <tr><td><b>Keadaan Tanah</b></td>
                            <c:if test="${edit}">
                                <td><s:textarea name="keadaantanah" rows="5" cols="130" class="normal_text"/></td>
                            </c:if>
                            <c:if test="${!edit}">
                                <td>${actionBean.keadaantanah} &nbsp;</td>
                            </c:if>
                        </tr>

                        <tr><td><b>Keputusan Terdahulu</b></td>
                            <c:if test="${edit}">
                                <td><s:textarea name="keputusandahulu" rows="5" cols="130" class="normal_text"/></td>
                            </c:if>
                            <c:if test="${!edit}">
                                <td>${actionBean.keputusandahulu} &nbsp;</td>
                            </c:if>
                        </tr>

                        <tr><td><b>Asas Pertimbangan</b></td>
                            <c:if test="${edit}">
                                <td><s:textarea name="asas" rows="5" cols="130" class="normal_text"/></td>
                            </c:if>
                            <c:if test="${!edit}">
                                <td>${actionBean.asas} &nbsp;</td>
                            </c:if>
                        </tr>

                        <tr><td><b>Ulasan PTD</b></td>
                            <c:if test="${edit}">
                                <td><s:textarea name="ulasanPtd" rows="5" cols="130" id="ulasanPtd" class="normal_text" readonly="true"/></td>   
                            </c:if>
                            <c:if test="${!edit}">
                                <td>${actionBean.ulasanPtd} &nbsp;</td>   
                            </c:if>
                        </tr>   

                        <tr><td><b>Perakuan PTG, Melaka</b></td>
                            <c:if test="${edit}">
                                <td><s:textarea name="perakuanptg" rows="5" cols="130" id="perakuanptg" class="normal_text" readonly="true"/></td>
                            </c:if>
                            <c:if test="${!edit}">
                                <td>${actionBean.perakuanptg} &nbsp;</td>
                            </c:if>
                        </tr>
                    </table> 
                </div>
            </div>
            <c:if test="${edit}">
                <p align="center">                                             
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>                                          
                </p>
            </c:if>            
        </fieldset>
    </div>

    <!--                    <tr><td>Pemohon</td>
                            <td><table border="0" width="96%">
    <c:set value="1" var="count"/>
    <c:forEach items="${senaraiPemohon}" var="pemohon" >
        <tr><td><font size="-1"><c:out value="${count}"/>) &nbsp;
        <c:out value="${pemohon.pihak.nama}"/>
    </font></td></tr>
        <c:set value="${count + 1}" var="count"/>
    </c:forEach>
</table>
</td>
</tr>-->
</s:form>
