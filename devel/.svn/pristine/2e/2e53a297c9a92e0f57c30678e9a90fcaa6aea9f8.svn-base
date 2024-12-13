<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<%
    DecimalFormat dcf = new DecimalFormat("#0.0000");
%>
<script type="text/javascript">
    function removeSingle(id)
    {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?deleteSingle&id='
                    + id;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    <%-- alert(ialertd);--%>
    }
    function tambahBaru(idMH) {
        // alert(idMH);
        window.open("${pageContext.request.contextPath}/pengambilan/pindaan/maklumat_hakmilikpengambilan?hakMilikPopup&idMH=" + idMH, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function kemaskiniLot(idMH) {
        // alert(idMH);
        window.open("${pageContext.request.contextPath}/pengambilan/pindaan/maklumat_hakmilikpengambilan?kemaskiniLot&idMH=" + idMH, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function refreshPageHakmilik() {
        var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?refreshpage';
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    $(document).ready(function() {

        var len = $(".daerah").length;


        for (var i = 0; i <= len; i++) {
            $('.hakmilik' + i).click(function() {//alert($(this).text());
                var idHM = $(this).text();
                if (idHM === 'TDK') {
                    alert('Hakmilik Tidak Dapat Dikesan ( TDK )');
                } else {//alert("ELSE");
                    window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?popup&idHakmilik=" + idHM, "eTanah",
                            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
                }


            });
        }
    });

    function validateLuas(idVar, rowNo) {

        var str = idVar.value;
        var luasTerlibat = parseFloat(idVar.value)
        //alert('LuasTerlibat Id: '+ luasTerlibat);

        var luas = parseFloat($('#luas' + rowNo).val());
        //var luas = parseFloat(document.getElementById("luas"+rowNo).value);
        //alert('Luas Id: '+ luas);
        //alert(luas);
        // var luas = parseInt(document.getElementById("luas"+rowNo).value);
        //  var luas = parseInt(document.getElementById("luas"+rowNo).value);
        //  alert('Luas'+luas);

        if (luasTerlibat > luas) {
            alert("Pastikan Luas Diambil tidak melebihi Luas");
            idVar.value = str.substring(0, str.length - 1);
            idVar.focus();
            return true;
        }
    }

    function MaskMoney(obj, evt) {
        var parts = obj.value.split('.');

        if (!((evt.keyCode == 190) ||
                (evt.keyCode == 8) ||
                (evt.keyCode == 188) ||
                (evt.keyCode == 144) ||
                (evt.keyCode == 96) ||
                (evt.keyCode == 97) ||
                (evt.keyCode == 98) ||
                (evt.keyCode == 99) ||
                (evt.keyCode == 100) ||
                (evt.keyCode == 101) ||
                (evt.keyCode == 102) ||
                (evt.keyCode == 103) ||
                (evt.keyCode == 104) ||
                (evt.keyCode == 105) ||
                (evt.keyCode == 110) ||
                (evt.keyCode >= 48 && evt.keyCode <= 57)))
            return false;

        var parts = obj.value.split('.');
        if (parts.length > 2)
            return false;
        if (evt.keyCode == 46)
            return (parts.length == 1);
        if (evt.keyCode == 8)
            return (parts.length > 0);
        if (parts[0].length >= 14)
            return false;
        if (parts.length == 2 && parts[1].length >= 4)
            return false;
    }

    function validateKodUnitLuas(idVar, rowNo) {
    <%--alert(idVar.value);--%>
        if (idVar.value == 'M') {
            var unitLuasDiambil = "mp";
            alert(unitLuasDiambil);
        }
        if (idVar.value == 'H') {
            var unitLuasDiambil = "Ha";
            alert(unitLuasDiambil);
        }

        var unitLuas = document.getElementById("unitLuas" + rowNo).value;
        alert(unitLuas);

    }

    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);

                }, 'html');

    }

    function simpanFlag(val, idPermohonan) {

        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/pindaan/maklumat_hakmilikpengambilan?simpanFlag&idMohonHakmilik=' + val + '&idmhnLama=' + idPermohonan;
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
    function simpanFlagActive(val, idPermohonan) {

        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/pindaan/maklumat_hakmilikpengambilan?simpanFlagActive&idMohonHakmilik=' + val + '&idmhnLama=' + idPermohonan;
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

<s:form beanclass="etanah.view.stripes.pengambilan.share.common.MaklumatHakmilikAmbilActionBean">
    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Tanah

            </legend>
            <!--<p align="center">
                <label><font color="black">Jumlah Keluasan Tanah : </font><font color=" blue">$//{actionBean.amountMH}&nbsp;mp&nbsp;&nbsp;(<--fmt:formatNumber pattern="#,##0.0000" value="$--{actionBean.convH}"/>&nbsp;&nbsp;hektar)</font></label>&nbsp;
            </p>
            <p align="center">!-->
            <center>
                <display:table class="tablecloth" name="${actionBean.listmaklumatTanahPengambilanForms}" pagesize="30" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumat_hakmilikpengambilan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                        <display:column title="ID Hakmilik" class="popup hakmilik${line_rowNum}">${line.mohonHakmilik.hakmilik.idHakmilik}</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik eq null}">
                        <display:column title="ID Hakmilik">TDK</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                        <display:column title="No Lot/No PT" >${line.mohonHakmilik.hakmilik.lot.nama}${line.mohonHakmilik.noLot}</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik eq null}">
                        <display:column title="No Lot/No PT" >${line.mohonHakmilik.lot.nama}${line.mohonHakmilik.noLot}</display:column>
                    </c:if>
                    <%-- <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                         <display:column title="Luas">
                             <s:hidden formatPattern="#0.0000" name="luas1" value="${line.mohonHakmilik.hakmilik.luas}" id="luas${line_rowNum-1}" />
                             <s:hidden name="unitLuas" value="${line.mohonHakmilik.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                             <fmt:formatNumber pattern="#,##0.0000" value="${line.mohonHakmilik.hakmilik.luas}"/>&nbsp;
                             <c:if test="${line.mohonHakmilik.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                             <c:if test="${line.mohonHakmilik.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                         </display:column>
                     </c:if>
                     <c:if test="${line.mohonHakmilik.hakmilik eq null}">--%>
                    <display:column title="Luas">
                        <s:hidden formatPattern="#0.0000" name="luas1" value="${line.mohonHakmilik.luasTerlibat}" id="luas${line_rowNum-1}" />
                        <s:hidden name="unitLuas" value="${line.mohonHakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.mohonHakmilik.luasTerlibat}"/>&nbsp;
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Ekar '}">Ekar</c:if>
                    </display:column>
                    <%-- </c:if>--%>
                    <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                        <display:column title="Daerah" class="daerah">${line.mohonHakmilik.hakmilik.daerah.nama}</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik eq null}"> 
                        <display:column title="Daerah" class="daerah">${line.mohonHakmilik.cawangan.daerah.nama}</display:column>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                        <display:column title="Bandar/Pekan/Mukim">${line.mohonHakmilik.hakmilik.bandarPekanMukim.nama} ${line.mohonHakmilik.hakmilik.seksyen.nama}</display:column><%--${fn:toLowerCase}--%>
                    </c:if>
                    <c:if test="${line.mohonHakmilik.hakmilik eq null}">
                        <display:column title="Bandar/Pekan/Mukim">${line.mohonHakmilik.bandarPekanMukimBaru.nama} ${line.mohonHakmilik.kodSeksyen.nama}</display:column>
                    </c:if>

                    <display:column title="Luas Diambil">
                        <fmt:formatNumber pattern="#,##0.0000" value="${line.luasAmbil}"/>&nbsp;
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                        <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Ekar '}">Ekar</c:if>

                    </display:column>

                    <display:column title="Baki Luas">

                        <c:if test="${line.mohonHakmilik.luasTerlibat ne null}">
                            <c:set value="${line.luasAmbil}" var="a"/>
                            <c:set value="${line.mohonHakmilik.luasTerlibat}" var="b"/>
                            <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>&nbsp;
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Ekar '}">Ekar</c:if>
                        </c:if>
                        <c:if test="${line.mohonHakmilik.luasTerlibat eq null}">0</c:if>
                        <%-- </c:if>--%>
                    </display:column>
                    <%--hakmilik.kegunaanTanah.nama--%>
                    <display:column property="mohonHakmilik.hakmilik.syaratNyata.syarat" title="Kegunaan Tanah" />
                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="tambahBaru('${line.mohonHakmilik.id}');" />
                        </div>
                    </display:column>
                    <c:if test="${line.mohonHakmilik.permohonan.kodUrusan.kod eq '831'}">
                        <display:column title="Hapus">
                            <div align="center">
                                <p align="center">
                                    <s:radio name="pilihanHakmilik" id="pilihanHakmilik" checked="checked" value="A" onclick="simpanFlag('${line.mohonHakmilik.id}','${line.mohonHakmilik.permohonan.idPermohonan}');"/> Ya &nbsp
                                </p>
                            </div>
                        </display:column>
                    </c:if>
                </display:table>
            </center>

            <%-- <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>

        </fieldset>

        <fieldset class="aras1" id="locate">
            <c:if test="${fn:length(actionBean.listmaklumatTanahPengambilanXactiveForms) > 0}">
                <legend>
                    Maklumat Tanah Tidak Berkuatkuasa

                </legend>
                <!--<p align="center">
                    <label><font color="black">Jumlah Keluasan Tanah : </font><font color=" blue">$//{actionBean.amountMH}&nbsp;mp&nbsp;&nbsp;(<--fmt:formatNumber pattern="#,##0.0000" value="$--{actionBean.convH}"/>&nbsp;&nbsp;hektar)</font></label>&nbsp;
                </p>
                <p align="center">!-->


                <center>
                    <display:table class="tablecloth" name="${actionBean.listmaklumatTanahPengambilanXactiveForms}" pagesize="30" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumat_hakmilikpengambilan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                            <display:column title="ID Hakmilik" class="popup hakmilik${line_rowNum}">${line.mohonHakmilik.hakmilik.idHakmilik}</display:column>
                        </c:if>
                        <c:if test="${line.mohonHakmilik.hakmilik eq null}">
                            <display:column title="ID Hakmilik">-</display:column>
                        </c:if>
                        <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                            <display:column title="No Lot/No PT" >${line.mohonHakmilik.hakmilik.lot.nama}${line.mohonHakmilik.noLot}</display:column>
                        </c:if>
                        <c:if test="${line.mohonHakmilik.hakmilik eq null}">
                            <display:column title="No Lot/No PT" >${line.mohonHakmilik.lot.nama}${line.mohonHakmilik.noLot}</display:column>
                        </c:if>
                        <%-- <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                             <display:column title="Luas">
                                 <s:hidden formatPattern="#0.0000" name="luas1" value="${line.mohonHakmilik.hakmilik.luas}" id="luas${line_rowNum-1}" />
                                 <s:hidden name="unitLuas" value="${line.mohonHakmilik.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                                 <fmt:formatNumber pattern="#,##0.0000" value="${line.mohonHakmilik.hakmilik.luas}"/>&nbsp;
                                 <c:if test="${line.mohonHakmilik.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                 <c:if test="${line.mohonHakmilik.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                             </display:column>
                         </c:if>
                         <c:if test="${line.mohonHakmilik.hakmilik eq null}">--%>
                        <display:column title="Luas">
                            <s:hidden formatPattern="#0.0000" name="luas1" value="${line.mohonHakmilik.luasTerlibat}" id="luas${line_rowNum-1}" />
                            <s:hidden name="unitLuas" value="${line.mohonHakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                            <fmt:formatNumber pattern="#,##0.0000" value="${line.mohonHakmilik.luasTerlibat}"/>&nbsp;
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Ekar'}">P</c:if>f
                        </display:column>
                        <%-- </c:if>--%>
                        <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                            <display:column title="Daerah" class="daerah">${line.mohonHakmilik.hakmilik.daerah.nama}</display:column>
                        </c:if>
                        <c:if test="${line.mohonHakmilik.hakmilik eq null}"> 
                            <display:column title="Daerah" class="daerah">${line.mohonHakmilik.cawangan.daerah.nama}</display:column>
                        </c:if>
                        <c:if test="${line.mohonHakmilik.hakmilik ne null}">
                            <display:column title="Bandar/Pekan/Mukim">${line.mohonHakmilik.hakmilik.bandarPekanMukim.nama} ${line.mohonHakmilik.hakmilik.seksyen.nama}</display:column><%--${fn:toLowerCase}--%>
                        </c:if>
                        <c:if test="${line.mohonHakmilik.hakmilik eq null}">
                            <display:column title="Bandar/Pekan/Mukim">${line.mohonHakmilik.bandarPekanMukimBaru.nama} ${line.mohonHakmilik.kodSeksyen.nama}</display:column>
                        </c:if>

                        <display:column title="Luas Diambil">
                            <fmt:formatNumber pattern="#,##0.0000" value="${line.luasAmbil}"/>&nbsp;
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                            <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                             <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Ekar'}">P</c:if>



                        </display:column>

                        <display:column title="Baki Luas">

                            <c:if test="${line.mohonHakmilik.luasTerlibat ne null}">
                                <c:set value="${line.luasAmbil}" var="a"/>
                                <c:set value="${line.mohonHakmilik.luasTerlibat}" var="b"/>
                                <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>&nbsp;
                                <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                <c:if test="${line.mohonHakmilik.kodUnitLuas.nama eq 'Ekar'}">P</c:if>
                            </c:if>
                            <c:if test="${line.mohonHakmilik.luasTerlibat eq null}">0</c:if>
                            <%-- </c:if>--%>
                        </display:column>
                        <%--hakmilik.kegunaanTanah.nama--%>
                        <display:column property="mohonHakmilik.hakmilik.syaratNyata.syarat" title="Kegunaan Tanah" />
                        <c:if test="${line.mohonHakmilik.permohonan.kodUrusan.kod eq '831'}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <p align="center">
                                        <s:radio name="pilihanHakmilik" id="pilihanHakmilik" checked="checked" value="A"  onclick="simpanFlagActive('${line.mohonHakmilik.id}','${line.mohonHakmilik.permohonan.idPermohonan}');"/> Ya &nbsp
                                    </p>
                                </div>
                            </display:column>
                        </c:if>

                    </display:table>
                </center>               
            </c:if>

        </fieldset>    

        <br>
        <div align="right">
            <c:if test="${actionBean.stageId eq 'kemaskini_maklumat'}">
                <s:submit name="hantar" id="save" value="Hantar" class="longbtn"/>   
            </c:if>
        </div>


    </div>


</s:form>
