<%-- 
    Document   : laporan_tanahV3
    Created on : Oct 14, 2020, 1:38:23 PM
    Author     : zipzap
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {


        var len = $(".daerah").length;

        for (var i = 0; i <= len; i++) {
            $('.hakmilik' + i).click(function() {
                window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?popup&idHakmilik=" + $(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600").focus();
            });
        }
    });


    function statusTanah1(jenisStatusTanah, idMohonHakmilik) {
//        alert("jenisStatusTanah =" + jenisStatusTanah);
//        alert("idMohonHakmilik = " + idMohonHakmilik);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/laporanTanah?statusTanah&jenisStatusTanah=" + jenisStatusTanah + "&idMohonHakmilik=" + idMohonHakmilik;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }



</script>
<s:form beanclass="etanah.view.laporanTanah.laporanTanahV3" name="form">

    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <s:hidden name="idMohonHakmilik" id="idMohonHakmilik"/>
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <%--<s:hidden name="idMohonHakmilik1" id="idMohonHakmilik1" value="${actionBean.hakmilikPermohonan.id}"/>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                <tfoot>
                    <tr>    
                        <td colspan="4"><center><b>Status Tanah</b></center></td>  
                </tr>
                </tfoot>
            </table>
            <br>
            <center>
                <s:radio name="jenisStatusTanah" value="kerajaan" onclick="statusTanah1('kerajaan','${actionBean.hakmilikPermohonan.id}')"/> Tanah Kerajaan /Lombong / Rizab / Rizab Hutan &nbsp; &nbsp;
                <s:radio name="jenisStatusTanah" value="milik" onclick="statusTanah1('milik','${actionBean.hakmilikPermohonan.id}')"/> Tanah Milik
            </center>
            <br>
        </fieldset>
        <br>
    </div>
    <c:if test="${actionBean.jenisStatusTanah eq 'milik'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                    <tfoot>
                        <tr>    
                            <td colspan="4"><center><b>Tanah Milik</b></center></td>  
                    </tr>
                    </tfoot>

                    <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                        <tr>
                            <th>No. Hakmilik</th>
                            <td>${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}</td>
                        </tr>
                        <tr>
                            <th>Luas</th>
                            <td>${actionBean.hakmilikPermohonan.hakmilik.luas}</td>
                        </tr>
                        <tr>
                            <th>Mukim/Pekan/Bandar</th>
                            <td>${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}</td>
                        </tr>
                        <tr>
                            <th>Daerah</th>
                            <td>${actionBean.hakmilikPermohonan.hakmilik.daerah.nama}</td>
                        </tr>
                        <tr>
                            <th>Lembar Piawai</th>
                            <td>${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}</td>
                        </tr>
                        <tr>
                            <th>No Pelan Akui</th>
                            <td>${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}</td>
                        </tr>
                    </table>
                    <br>
                    <center>
                        <%--<s:button name="simpanKedudukanTanah" id="" value="simpan" class="btn" onclick="simpanKedudukanTanah('${line4.id}','${actionBean.idPermohonan}')"/>--%> 

                    </center>
                </table>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${actionBean.jenisStatusTanah eq 'kerajaan'}">
        <div class="subtitle">
            <fieldset class="aras1">

                <c:if test="${fn:length(actionBean.listMohonStatusTanah) > 0}">
                    <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                        <tfoot>
                            <tr>    
                                <td colspan="4"><center><b>Perihal Permohonan</b></center></td>  
                        </tr>
                        </tfoot>

                        <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2"> 
                            <c:forEach items="${actionBean.listMohonStatusTanah}" var="item" varStatus="line">
                                <tr>

                                    <c:if test="${item.kodStatusTanah.kod eq '01'}">
                                        <th>${item.kodStatusTanah.perihal}</th>
                                        <td>  <s:textarea name="jenisTanah1" value="${item.keterangan}" id="jenisTanah" rows="3" cols="100"/></td>
                                    </c:if>   
                                </tr>
                                <tr>
                                    <c:if test="${item.kodStatusTanah.kod eq '02'}">
                                        <th>${item.kodStatusTanah.perihal}</th>
                                        <td>  <s:textarea name="tujuan1" value="${item.keterangan}" id="tujuan" rows="3" cols="100"/></td>
                                    </c:if>   
                                </tr>
                                <tr>
                                    <c:if test="${item.kodStatusTanah.kod eq '03'}">
                                        <th>${item.kodStatusTanah.perihal}</th>
                                        <td>  <s:textarea name="rujFail1" value="${item.keterangan}" id="rujFail" rows="3" cols="100"/></td>
                                    </c:if>   
                                </tr>
                                <tr>
                                    <c:if test="${item.kodStatusTanah.kod eq '04'}">
                                        <th>${item.kodStatusTanah.perihal}</th>
                                        <td>  <s:textarea name="noWarta1" value="${item.keterangan}" id="noWarta" rows="3" cols="100"/></td>
                                    </c:if>   
                                </tr>
                                <tr>
                                    <c:if test="${item.kodStatusTanah.kod eq '05'}">
                                        <th>${item.kodStatusTanah.perihal}</th>
                                        <td>  <s:textarea name="seksyen1" value="${item.keterangan}" id="seksyen" rows="3" cols="100"/></td>
                                    </c:if>   
                                </tr>
                                <tr>
                                    <c:if test="${item.kodStatusTanah.kod eq '06'}">
                                        <th>${item.kodStatusTanah.perihal}</th>
                                        <td>  <s:textarea name="pelanWarta1" value="${item.keterangan}" id="pelanWarta" rows="3" cols="100"/></td>
                                    </c:if>   
                                </tr>
                                <tr>
                                    <c:if test="${item.kodStatusTanah.kod eq '07'}">
                                        <th>${item.kodStatusTanah.perihal}</th>
                                        <td>  <s:textarea name="pengawal1" value="${item.keterangan}" id="pengawal" rows="3" cols="100"/></td>
                                    </c:if>   
                                </tr>                              
                                <tr>
                                    <c:if test="${item.kodStatusTanah.kod eq '08'}">
                                        <th>${item.kodStatusTanah.perihal}</th>
                                        <td>  <s:textarea name="diberimilik1" value="${item.keterangan}" id="diberimilik" rows="3" cols="100"/></td>
                                    </c:if>   
                                </tr>
                            </c:forEach>
                        </table>
                        <BR>
                        <CENTER>
                             <s:button name="simpanStatusTanah" id="" value="simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </CENTER>

                        <br>
                    </table>
                    <br>
                </c:if>
            </fieldset>
        </div>
    </c:if>




</s:form>
