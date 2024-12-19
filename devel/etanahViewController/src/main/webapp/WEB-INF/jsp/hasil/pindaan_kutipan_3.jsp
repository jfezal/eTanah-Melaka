<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#hm").val("0");
        $("#hm1").val("");
        $("#daftar").attr("disabled", "true");
        $('#myform input:text').each( function(el){
            $(this).blur( function() {
                $(this).val( $(this).val().toUpperCase());
            });
        });
    });
</script>
<script type="text/javascript">
    function edit(f, id1){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/pindaan_kutipan?cari&"+queryString+"&accBaru="+id1, "eTanah",
                "status=0,scrollbars=1,resizable=1,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function addRows(radioBtn, baki){
        $('#hakmilik').val(radioBtn);
        $('#hm').val(baki);
        var x = parseFloat(baki);
        $('#hm1').val(x.toFixed(2));
        $('#daftar').removeAttr("disabled");
        refreshPage();
        //validate akaun sama mesti kurang daripada akaun asal
        //validate();
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/hasil/pindaan_kutipan?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
    }

    // check akaun baru mesti kurang dpd akaun asal_-_POPUP : START
    function validate(){
        var j = document.getElementById('jum');
        var k = document.getElementById('hm');
        var baru = parseFloat(k.value);
        var asal = parseFloat(j.value);
        if(baru > asal){
            alert("Amaun Cukai Tanah bagi ID Hakmilik baru hendaklah kurang atau sama dengan amaun Hakmilik Asal");
            $('#hakmilik').val("");
            $('#hm').val("");
            $("#daftar").attr("disabled", "true");
        }else if (baru <= 0){
            alert("Cukai Tanah bagi ID Hakmilik "+$("#hakmilik").val()+" untuk tahun ini telah dijelaskan.");
            $("#daftar").attr("disabled", "true");
        }else {
            $('#daftar').removeAttr("disabled");
        }
    }
    //END

    // check akaun baru mesti kurang dpd akaun asal : START
    function checking(){
        var id = document.getElementById('hakmilik');
        $.get("${pageContext.request.contextPath}/hasil/pindaan_kutipan?doCheckBalance1&idHakmilik="+$("#hakmilik").val(),
        function(data){
            if(data == 'xx'){
                $('#hm').val(data);
                $('#daftar').removeAttr("disabled");
                getBaki();
                //validator();
            }
            else if(data =='B'){
                alert("Akaun bagi "+id.value+" telah dibatalkan.");
                $('#hakmilik').val("");
                $('#hm').val("");
                $("#daftar").attr("disabled", "true");
            }
            else if(data =='0'){
                alert("Cukai Tanah bagi ID Hakmilik "+id.value+" untuk tahun ini telah dijelaskan.");
                $('#hakmilik').val("");
                $('#hm').val("");
                $("#daftar").attr("disabled", "true");
            }else if(data =='1'){
                alert("Tiada rekod Cukai Tanah bagi Nombor Akaun "+id.value+".");
                $('#hakmilik').val("");
                $('#hm').val("");
                $("#daftar").attr("disabled", "true");
            }
        });
    }

    function getBaki(){
        $.get("${pageContext.request.contextPath}/hasil/pindaan_kutipan?getAmaun&idHakmilik="+$("#hakmilik").val(),
        function(data){
            if(data == "-"){
                $('#hm1').val("-");
            }else{
                var m = parseFloat(data);
                $.get("${pageContext.request.contextPath}/hasil/pindaan_kutipan?getAkaunBaru&idHakmilik="+$("#hakmilik").val(),
                function(d){
                    if(data != "-"){$('#caw').val(d);}
                });
                $('#hm1').val(m.toFixed(2));
            }
        });
    }

    function validator(){
        var a = document.getElementById('jum');
        var lama = parseInt(a.value);
        var b = document.getElementById('hm');
        if(b.value != "xx"){
            var baru = parseInt(b.value);
            if(baru > lama){
                alert("Amaun Cukai Tanah bagi ID Hakmilik baru hendaklah kurang atau sama dengan amaun Hakmilik Asal");
                $('#hakmilik').val("");
                $('#hm').val("");
                $("#daftar").attr("disabled", "true");
            }
            if(baru == 0){
                alert("Cukai Tanah bagi ID Hakmilik "+$("#hakmilik").val()+" untuk tahun ini telah dijelaskan.");
                $('#hakmilik').val("");
                $('#hm').val("");
                $("#daftar").attr("disabled", "true");
            }else{
                $('#daftar').removeAttr("disabled");
            }
        }

    }
    //END

//    function validateHakmilik(f){
//        var kodCaw = (document.getElementById('pguna')).value;
//        var kodCawHakmilikBaru = (document.getElementById('hakmilik')).value;
//        var kod = null;
//        var kn = (document.getElementById('kn')).value;
//        if(kn == 'melaka'){
//            kod = (document.getElementById('caw')).value;
//        }else{
//            kod = kodCawHakmilikBaru.substring(2, 4);}
//        
////        if(kodCaw != kod){
////            alert("Tidak Dibenarkan untuk membuat pindahan bagi Hakmilik daerah lain.");
////            $('#hakmilik').val("");
////            $('#hm1').val("");
////            return false;}
//        return true;
//    }
</script>
        
<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindaan Kutipan</font>
                    </c:if>
                    <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindahan Kutipan</font>
                    </c:if>
                </div>
            </td>
        </tr>
</table></div>

<s:form beanclass="etanah.view.stripes.hasil.PindaanKutipanActionBean" id="myform">
    <s:hidden name="akaun.noAkaun"/>
    <s:hidden name="${actionBean.byran}"/>
    <s:hidden name="${actionBean.pengguna.kodCawangan.kod}" value="${actionBean.pengguna.kodCawangan.kod}" id="pguna"/>
    <s:hidden name="${actionBean.kodNegeri}" value="${actionBean.kodNegeri}" id="kn"/>
    <s:text name="" id="caw" style="display:none"/>
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kutipan</legend>
            <p>
                <label>Tarikh Pembayaran :</label>
                <fmt:formatDate value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy" />
                &nbsp;
            </p>

            <p>
                <label>Nombor Resit :</label>
                ${actionBean.dokumenKewangan.idDokumenKewangan}
            </p>

            <p>
                <label>Jumlah Bayaran :</label>
                <fmt:formatNumber value="${actionBean.dokumenKewangan.amaunBayaran}" type="currency" currencySymbol="RM "/>
                <s:text name="${actionBean.dokumenKewangan.amaunBayaran}" value="${actionBean.dokumenKewangan.amaunBayaran}" id="jum" style="display:none"/>
                &nbsp;
            </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Akaun Asal</legend>
            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <p>
                    <label>Nombor Akaun :</label>
                    ${actionBean.akaun.noAkaun}
                </p>
            </c:if>
            <p>
                <label>ID Hakmilik Asal :</label>
                ${actionBean.akaun.hakmilik.idHakmilik}
            </p>
            <p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.transaksiList}" id="line">
                        <display:column title="Bil." style="text-align:center">${line_rowNum}.</display:column>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Masuk" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                        <display:column title="Transaksi">${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>
                        <display:column title="No. Resit">${line.dokumenKewangan.idDokumenKewangan}</display:column>
                        <display:column title="Akaun Debit (RM)" style="text-align:right">
                                <c:if test="${line.status.kod eq 'A' and line.kodTransaksi.kod ne '99000'
                                              and line.kodTransaksi.kod ne '99001'and line.kodTransaksi.kod ne '99002'
                                              and line.kodTransaksi.kod ne '99051'and line.kodTransaksi.kod ne '99050'
                                              and line.kodTransaksi.kod ne '99003'and line.kodTransaksi.kod ne '99030' }">
                                    <fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/>
                                </c:if>
                            </display:column>
                            <display:column title="Akaun Kredit (RM)" style="text-align:right">
                                <c:if test="${line.status.kod ne 'A'}">
                                    <fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/>
                                </c:if>
                                <c:if test="${line.status.kod eq 'A' and line.kodTransaksi.kod eq '99002'
                                              or line.kodTransaksi.kod eq '99000'or line.kodTransaksi.kod eq '99001'
                                              or line.kodTransaksi.kod eq '99051'or line.kodTransaksi.kod eq '99050'
                                              or line.kodTransaksi.kod eq '99003'or line.kodTransaksi.kod eq '99030'}">
                                    <fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/>
                                </c:if>
                            </display:column>
                        <display:column title="Dimasuk Oleh" property="infoAudit.dimasukOleh.nama"/>
                        
                    </display:table>
                </div>
        </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Akaun Baru</legend>
            <p class=instr>Sila masukkan ID Hakmilik baru dan tekan butang Cari untuk membuat carian.</p>
                <p>
                    <c:choose>
                        <c:when test="${actionBean.kodNegeri eq 'melaka'}">
                            <label><em>*</em>Nombor Akaun Baru :</label>
                        </c:when>
                        <c:otherwise>
                            <label><em>*</em>ID Hakmilik Baru :</label>
                        </c:otherwise>
                    </c:choose>
                    <%--<s:text name="accBaru" id="hakmilik" onblur="check(this.form, '${actionBean.accBaru}');" />--%>
                    <s:text name="accBaru" id="hakmilik" onblur="checking();" />
                    <s:text name="" id="hm" style="display:none;"/>
                    <!--s:button name=" " onclick="edit(this.form);" value="Cari" class="btn"/-->
                </p>
                <p>
                    <label>Baki Bagi ID Hakmilik Baru (RM) :</label>
                    <s:text name="" id="hm1" readonly="true" style="background:transparent;border:0 px;"/>
                </p>
        </fieldset>
    </div>

    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                <s:submit name="save" value="Simpan" class="btn" id="daftar" onclick="return validateHakmilik(this.form);"/>
                <s:submit name="kembali" value="Kembali" class="btn"/>
            </td>
        </tr>
    </table></div>

</s:form>