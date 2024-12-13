<%--
    Document   : carian_PendudukanSementaraTerdahulu
    Created on : Apr 14, 2011, 5:06:28 PM
    Author     : massita
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function validateForm(){

        if(document.form2.idSblm.value=="")
        {
            alert("Sila masukkan Id Permohonan Terdahulu");
            return false;
        }
        return true;
    }

    function search(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pengambilan/pemulihanTanahCarianPendudukanSementara?IdMohonOSebabPopup=';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }


    function test(){
        document.getElementById("idSblm").value ="";
        document.getElementById("namaProjek").value ="";
    }

    function hideMaklumatPemohon(){
        $('#MaklumatPemohon').hide();
        $('#errors').hide();
    }

    var message="Tidak boleh copy dan paste data lakukan carian dahulu!";
    function clickIE4(){ if (event.button==2){ alert(message); return false; } }
    function clickNS4(e){ if (document.layers||document.getElementById&&!document.all)
        { if (e.which==2||e.which==3){ alert(message); return false; } } }
    if (document.layers){ document.captureEvents(Event.MOUSEDOWN); document.onmousedown=clickNS4; }
    else if (document.all&&!document.getElementById){ document.onmousedown=clickIE4; }
    document.oncontextmenu=new Function("alert(message);return false")
</script>



<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pengambilan.carian_permohonanPendudukanSementaraActionBean" name="form2">
        <s:messages/>
        <div id="errors">
            <s:errors/>
        </div>
        <s:hidden id="hakmilikList" name="hakmilikList" />
        <fieldset class="aras1">
            <legend>Semakan Maklumat Permohonan Penggunaan Sementara Tanah</legend><br />
            <div class="content" align="center">
                <table border="0" cellspacing="5" width="80%">
                    <tr>
                        <td align="left" width="30%" valign="top"><label style="; clear: right"  ><font color="red">*</font>Id permohonan yang terdahulu :</label></td>
                        <td><s:text name="idSblm" size="30" id="idSblm" onkeyup="this.value=this.value.toUpperCase();" readonly="true" value="${actionBean.idPermohonan}"/><s:button class="btn" value="Cari" name="add" onclick="javascript:search()"/></td>
                    </tr>

                    <tr>
                        <td align="right"><label>Tujuan Permohonan :</label></td>
                        <td><s:textarea name="namaProjek" rows="3" cols="40" id="namaProjek" onkeyup="this.value=this.value.toUpperCase();" readonly="true"/></td>
                        <td></td>
                    </tr>

                    <tr>
                        <td align="right"><s:button name="checkSebelum" value="Semak" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/></td>
                        <td><s:button name="reset" value="Isi Semula" class="btn" onclick="test();"/></td>
                    </tr>
                </table>
            </div>
        </fieldset> <br />
        <div id="MaklumatPemohon">
            <c:if test="${Maklumat_Pemohon}">
                <fieldset class="aras1">
                    <legend>Maklumat Pemohon</legend>
                    <div class="content" align="center">
                        <table border="0" width="80%"  cellspacing="5">

                            <tr><td align="left" width="30%" valign="top"><label style="; clear: right"  >Pemohon :</label></td>
                                <td>
                                    <font style="text-transform: uppercase">
                                        <c:set var="count" value="1"/>
                                        <c:forEach items="${actionBean.pemohonList}" var="list">
                                            <c:out value="${count}" />)
                                            <c:out value="${list.pihak.nama}"/><br/>
                                            <c:set var="count" value="${count+1}"/>
                                        </c:forEach>
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <%--<td align="left" width="30%" valign="top"><label style="; clear: right" ><c:if test="${list.pihak.jenisPengenalan.kod eq 'B' }">No K/P Baru :</c:if></label></td>--%>
                                <td>
                                    <font style="text-transform: uppercase">
                                        <c:set var="count" value="1"/>
                                        <c:forEach items="${actionBean.pemohonList}" var="list">
                                            <tr>
                                                <td align="left" width="30%" valign="top">
                                                    <label style="; clear: right" >
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'B' }">No K/P Baru :</c:if>
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'L' }">No K/P Lama :</c:if>
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'D' }">No Pendaftaran :</c:if>
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'F' }">No Paksa :</c:if>
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'I' }">No Polis :</c:if>
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'K' }">No MyKid :</c:if>
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'N' }">No Bank :</c:if>
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'P' }">No Pasport :</c:if>
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'S' }">No Syarikat :</c:if>
                                                        <c:if test="${list.pihak.jenisPengenalan.kod eq 'U' }">No Pertubuhan :</c:if>
                                                        <c:if test="${list.pihak.noPengenalan ne null && list.pihak.jenisPengenalan.kod eq '0' }">No Pengenalan/No Syarikat :</c:if>
                                                    </label>
                                                </td>

                                                <td><c:out value="${list.pihak.noPengenalan}"/></td>
                                            </tr>
                                        </c:forEach>
                                    </font>
                                </td>
                            </tr>

                            <tr><td align="left" width="30%" valign="top"><label style="; clear: right"  >Alamat :</label></td>
                                <td>
                                    <font style="text-transform: uppercase">
                                        <c:set var="count" value="1"/>
                                        <c:forEach items="${actionBean.pemohonList}" var="list">
                                            <c:out value="${list.pihak.alamat1}"/><br/>
                                            <c:out value="${list.pihak.alamat2}"/><br/>
                                            <c:out value="${list.pihak.alamat3}"/><br/>
                                            <c:out value="${list.pihak.alamat4}"/><br/>
                                            <c:out value="${list.pihak.poskod}"/><br/>
                                        </c:forEach>
                                    </font>
                                </td>
                            </tr>
                            <tr><td align="left" width="30%" valign="top"><label style="; clear: right"  >Jenis Kepentingan :</label></td>
                                <td>
                                    <font style="text-transform: uppercase">
                                        <c:set var="count" value="1"/>
                                        <c:forEach items="${actionBean.pemohonList}" var="list">
                                            <c:if test="${list.jenis.nama eq null}"> Tiada Data </c:if>
                                            <c:out value="${list.jenis.nama}"/><br/>
                                        </c:forEach>
                                    </font>
                                </td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
            </c:if>
        </div>
    </div>
</s:form>