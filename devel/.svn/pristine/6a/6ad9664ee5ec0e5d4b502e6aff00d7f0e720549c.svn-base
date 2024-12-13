<%--
    Document   : perintah
    Created on : July 12, 2010, 11:20:12 AM
    Author     : Rajesh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">


    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    function save(){
        self.opener.refreshPageTanahRizab();
        self.close();}

    function validation() {
        if($("#idhakmilik").val() == ""){
            alert('Sila masukkan " No H/M " terlebih dahulu.');
            $("#idhakmilik").focus();
            return true;
        }

    }

    function savePenjaga(event, f){
        if(validation()){

        }
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
    <%--self.opener.refreshPageHakmilik();--%>
                    self.close();
                },'html');
            }
        }
        $(document).ready(function() {
            $("#close").click( function(){
                setTimeout(function(){
                    self.close();
                }, 100);
            });
        });



</script>





<s:form beanclass="etanah.view.stripes.pengambilan.NotaSiasatanActionBean">
    <s:messages/>

    <div class="subtitle displaytag">
        <fieldset class="aras1"><br/>
            <legend align="left">
                <b>Perintah</b>
            </legend><br/>
            <p align="center">
                <font size="2">
                    Saya berpuas hati dengan keterangan yang diberi. Berdasarkan kepada prinsip-prinsip yang telah<br>
                    ditetapkan dalam Jadual Pertama Akta Pengambilan Tanah 1960,dan setelah mengambilkira pandangan dan nasihat<br>
                    yang diberi oleh Pengarah Penilaian dan Perkhidmatan Harta nilai yang berpatutan dan mencukupi ialah<br>
                    <b>RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilikPerbicaraan.keputusanNilai}"/>&nbsp;/se${actionBean.hakmilikPerbicaraan.kodUOM.nama} </b>
                    . Saya perintahkan supaya dibayar pampasan untuk kawasan yang terlibat dengan pengambilan ini<br>
                    seluas <b><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/>&nbsp;${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</b> di atas lot <b>${actionBean.hakmilik.noLot}</b> Hakmilik <b>${actionBean.hakmilik.noHakmilik}</b><br>
                    <%--<b>${actionBean.hakmilik.bandarPekanMukim.nama}</b>--%> kepada penama seperti berikut : <%--<b>RM <fmt:formatNumber pattern="#,##0.00" value="${((actionBean.hakmilikPerbicaraan.keputusanNilai)/ 1000)*(actionBean.hakmilikPermohonan.luasTerlibat)}"/></b>--%>.<br>
                </font>
            </p>
            <br/>
            <br/>

            <legend align="left">
                <b>Pampasan di bayar seperti berikut</b>
            </legend><br/>

            <p align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiPermohonanPihak}" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/nota_siasatan" id="line" pagesize="5">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Pemilik Berdaftar" />
                    <display:column property="pihak.noPengenalan" title="No KP" />
                    <display:column title="Bahagian" >
                        ${line.syerPembilang}/${line.syerPenyebut}
                    </display:column>
                    <display:column title="Nilai Tanah (RM)">
                        <c:set value="${line.syerPembilang}" var="a"/>
                        <c:set value="${line.syerPenyebut}" var="b"/>
                        <c:set value="${(actionBean.hakmilikPermohonan.luasTerlibat)}" var="c"/>
                        <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                        <c:if test="${line.syerPembilang ne 0 && line.syerPenyebut ne 0}">
                            <c:set value="${d*(a/b)}" var="e"/>
                        </c:if>
                        <fmt:formatNumber pattern="#,##0.00" value="${e}"/>
                    </display:column>

                    <display:column title="Nilaian Pokok/Bangunan/Lain-lain (RM)">
                        <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                            <c:if test="${line.pihak.idPihak == list.pihak.pihak.idPihak}">
                                <c:set var="B" value="0"/>
                                <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                    <c:set value="${B + list1.amaun}" var="B"/>
                                </c:forEach>
                                <display:table name="${list.senaraiPenilaian}" id="sub">
                                    <display:column property="item" title="Item" />
                                    <display:column title="Amaun" >
                                    <div align="right">
                                        <fmt:formatNumber pattern="#,##0.00" value="${sub.amaun}"/>
                                    </div>
                                </display:column>
                                <display:footer>
                                    <tr>
                                        <td><div align="left"><b>Total(RM):</b></div></td>
                                        <td><div align="right">
                                                <fmt:formatNumber pattern="#,##0.00" value="${B}"/>
                                            </div>
                                        </td>
                                    </tr>
                                </display:footer>
                            </display:table>
                        </c:if>
                    </c:forEach>
                    <%-- <c:set value=" ${actionBean.hakmilikPerbicaraan.penilaiAmaun}" var="c"/>--%>
                    <fmt:formatNumber pattern="#,##0.00" value=""/>
                </display:column>
                <%--<display:column title="Elaun Hadir (RM)">
                    <c:set value="${line.syerPembilang}" var="a"/>
                    <c:set value="${line.syerPenyebut}" var="b"/>
                    <c:set value=" ${((actionBean.hakmilikPerbicaraan.keputusanNilai)/ 1000)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="c"/>
                   <c:set value="10000" var="c"/>
                    <fmt:formatNumber pattern="#,##0.00" value="${a/b*c}"/>
                </display:column>--%>
                <display:column title="Jumlah (RM)">
                    <c:set value="${line.syerPembilang}" var="a"/>
                    <c:set value="${line.syerPenyebut}" var="b"/>
                    <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                    <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                    <c:if test="${line.syerPembilang ne 0 && line.syerPenyebut ne 0}">
                        <c:set value="${d*(a/b)}" var="e"/>
                    </c:if>
                    <c:set value="${e}" var="f"/>



                    <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                        <c:if test="${line.pihak.idPihak == list.pihak.pihak.idPihak}">
                            <c:set var="B" value="0"/>
                            <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                <c:set value="${B + list1.amaun}" var="B"/>
                            </c:forEach>
                            <c:set value="${B}" var="g"/>
                        </c:if>
                    </c:forEach>
                    <fmt:formatNumber pattern="#,##0.00" value="${f+g}"/>
                    <%--<fmt:formatNumber pattern="#,##0.00" value="${a+b+c}"/>--%>
                </display:column>
            </display:table>
            </p>
            <br/>
            <p>
                <label for="catatan">Perintah :</label>
                <s:textarea name="hakmilikPerbicaraan.jenisPembangunan" rows="3" cols="50" id="catatan"/>
            </p>
            <p align="center">
                <br>
                <%--<s:button name="simpanPerintah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                <s:button name="simpanPerintah" id="simpan" value="Simpan" class="btn" onclick="savePenjaga(this.name, this.form);"/>
                <%--<s:button name="janaPerintah" id="print" value="Jana Dokumen" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset>
    </div>
</s:form>

