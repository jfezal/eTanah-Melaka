<%-- 
    Document   : mmknForEtapp
    Created on : Feb 10, 2014, 5:13:42 PM
    Author     : nurashidah
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<script language="javascript" type="text/javascript">
    function addRow6(tableID6) {
        $("#rowCount6").val(1);
    <%--document.form2.rowCount6.value = 1;--%>
            var table = document.getElementById(tableID6);

            var rowCount6 = table.rows.length;
            var row = table.insertRow(rowCount6);

            var cell2 = row.insertCell(0);
            cell2.innerHTML = "<b>" + "4." + (rowCount6 + 1) + "</b>";

            var cell1 = row.insertCell(1);
            var element1 = document.createElement("textarea");
            element1.t = "kandungan6" + (rowCount6 + 1);
            element1.rows = 5;
            element1.cols = 150;
            element1.name = "kandungan6" + (rowCount6 + 1);
            element1.id = "kandungan6" + (rowCount6 + 1);
            cell1.appendChild(element1);
            //alert(element1.name)
    <%--document.form2.rowCount6.value=rowCount6 +1;--%>
            $("#rowCount6").val(rowCount6 + 1);
        }
        function deleteRow6()
        {
            var k = $("#rowCount6").val();
    <%--var k = document.form2.rowCount6.value;--%>
            var x = document.getElementById('dataTable6').rows[k - 1].cells;
            var idKandungan = x[0].innerHTML;

            document.getElementById('dataTable6').deleteRow(k - 1);
            document.form2.rowCount6.value = k - 1;
            $("#rowCount6").val(k - 1);

            if (document.getElementById('idKandungan6' + (k)) != null) {
                var url = '${pageContext.request.contextPath}/pengambilan/mmknEtapp?deleteSingle&idKandungan='
                        + idKandungan;

                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.MMKNPengambilanEtappActionBean"  id="formEtapp">
    <s:messages/>
    <s:errors/>

    <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
    <div class="subtitle">

        <fieldset class="aras1" id="locate">
            <div class="content" align="center">
                <table border="0" width="80%">

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td>
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.tujuanList1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea name="pemohon"  value="${line.subtajuk}" disabled="true" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>

                            </table>


                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>
                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
                    <tr><td>
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.permohonanList1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea name="pemohon"  value="${line.subtajuk}" disabled="true" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>

                            </table>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.2 Perihal Tanah</b></td></tr>
                    <tr><td>
                            <table >
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.tanahList1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea name="tanah" value="${line.subtajuk}" rows="5" disabled="true" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>

                            </table>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.3 Perihal Pemohon</b></td></tr>
                    <tr><td>
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.pemohonList1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea name="pemohon"  value="${line.subtajuk}" disabled="true" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>

                            </table>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>2.4 Anggaran Pampasan</b></td></tr>
                    <tr><td>
                            <table >
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.pampasanList1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea name="pampasan"  value="${line.subtajuk}" disabled="true" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>

                            </table>


                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>3. ULASAN JABATAN - JABATAN TEKNIKAL </b></td></tr>

                    <tr><td>
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.ulasanList1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea name="pemohon"  value="${line.subtajuk}" disabled="true" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>

                            </table>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>4. PANDANGAN YB ADUN KAWASAN </b></td></tr>
                    <tr><td>
                            <table>
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.YBList1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font><s:textarea name="YB"  value="${line.subtajuk}" rows="5" cols="150" disabled="true">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>

                            </table>


                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>5. PANDANGAN PENTADBIR TANAH </b></td></tr>
                    <tr><td>
                            <table>
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.pandanganPT21}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea name="tanah" value="${line.subtajuk}" rows="5" disabled="true" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>
                            </table>


                    <tr><td> &nbsp;</td></tr> <tr><td>
                            <table>
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.pandanganPT1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font><s:textarea name="YB"  value="${line.subtajuk}" rows="5" cols="150" disabled="true">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>
                                <%--<c:if test="${actionBean.pandanganPT1 eq null}">

                                    <tr style="font-weight:bold"><td style="display:none"></td><td>5.1</td>
                                        <td><font><s:textarea name="pandanganPT1"  value="${line.subtajuk}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:if>   --%> 

                            </table>
                        </td></tr>
                    <tr><td> &nbsp;</td></tr>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b>6. PERAKUAN PENTADBIR TANAH </b></td></tr>
                    <tr><td>                       
                            <table>

                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.perakuanPT21}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font <%--style="text-transform: uppercase"--%>><s:textarea name="tanah" value="${line.subtajuk}" rows="5" disabled="true" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>

                            </table>
                                </td></tr>

                    <tr><td> &nbsp;</td></tr>
                     <tr><td>
                            <table>

                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.perakuanPT1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font><s:textarea name="YB"  value="${line.subtajuk}" rows="5" cols="150" disabled="true">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>
                                <%--<c:if test="${actionBean.perakuanPT1 eq null}">

                                    <tr style="font-weight:bold"><td style="display:none"></td><td>6.1.1</td>
                                        <td><font><s:textarea name="perakuanPT11"  value="${line.subtajuk}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:if>  --%>       
                            </table>
                        </td></tr>

                    <tr><td> &nbsp;</td></tr>
                    <tr><td><b><font><%-- style="text-transform:uppercase"--%>7. PERAKUAN PENGARAH TANAH DAN GALIAN</font></b></td></tr>


                    <table>
                        <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.perakuanPTG1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td><c:out value="${line.subtajuk}"/></td>
                                        <td><font><s:textarea name="YB"  value="${line.subtajuk}" rows="5" cols="150" disabled="true">${line.kandungan}</s:textarea></font></td>
                                        </tr>

                                </c:forEach>
                        <%-- <c:if test="${actionBean.perakuanPTG1 eq null}">
                        <tr style="font-weight:bold"><td style="display:none"></td><td>7.1</td>
                            <td><font><s:textarea name="perakuanPTG11"  value="${line.subtajuk}" rows="5" cols="150">${line.kandungan}</s:textarea></font></td>
                            </tr>
                    </c:if>--%> 
                    </table>
                    </td></tr>

                    <tr><td> &nbsp;</td></tr>


                    </fieldset>
            </div><%--<c:if test="${actionBean.pandanganPT1 eq null} || ${actionBean.perakuanPT1 eq null} || ${actionBean.perakuanPTG eq null}">
<p align="center">
            <%--<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
            <%--<s:hidden name="idKertas" value="${actionBean.idKertas}"/>
            <%--<s:hidden name="idKandungan" value="${permohonanKertasKandungan.idKandungan}"/>--%>
            <%--<s:button name="simpan" id="save" value="Simpan" class="btn"/>
            <%--|| if(validateForm2()) || if(validateForm3()) || if(validateForm4()) || if(validateForm5()) || if(validateForm6()) || if(validateForm7())
        </p></c:if>--%> 

        </s:form>