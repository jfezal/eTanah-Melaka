<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">
//    function change(f){
//        if (f == 'KUT'){
//            $('#permohonan').hide();
//            $('#dokKewangan').show();
//            $('#manual').show();
//            $('#akaun').show();
//            $('#hakmilik').show();
//            $('#msg1').hide();
//            $('#msg2').show();
//            $('#msg3').hide();
//            $('#l1').show();
//            $('#l2').hide();
//        }
//        else if (f == 'URN'){
//            $('#hakmilik').hide();
//            $('#akaun').hide();
//            $('#manual').hide();
//            $('#dokKewangan').show();
//            $('#permohonan').show();
//            $('#msg1').hide();
//            $('#msg2').hide();
//            $('#msg3').show();
//            $('#l1').hide();
//            $('#l2').show();
//        }
//        else{
//            $('#hakmilik').hide();
//            $('#permohonan').hide();
//            $('#dokKewangan').hide();
//            $('#msg1').show();
//            $('#msg2').hide();
//            $('#msg3').hide();
//        }
//    }

    function chooseReceipt(resit, caw, row){
        $('#resit').val(resit);
        $('#cawResit').val(caw);
        $.get("${pageContext.request.contextPath}/hasil/pindaan_kutipan?doCheckReceipt&resit="+resit,
        function(data){
            if(data == '0'){
                alert("Nombor Resit ini telah digunakan untuk pindaan.");
                $('#next').attr("disabled", "true");
            }else{
                $('#next').removeAttr("disabled");
            }
        });
    }

    function validateReceipt(f){
        var kn = (document.getElementById('kodNegeri')).value;
        var kodCaw = (document.getElementById('pguna')).value;
        var caw = (document.getElementById('cawResit')).value;
        var kodCawResit = (document.getElementById('resit')).value;
        var kod =null;
        if(kn == 'melaka'){
            kod = kodCawResit.substring(1, 3);
            if(kodCaw != caw){
                alert("Tidak Dibenarkan untuk membuat pindahan bagi resit daerah lain.");
                return false;}
        }else{
            kod = kodCawResit.substring(6, 8);
            if(kodCaw != caw){
                alert("Tidak Dibenarkan untuk membuat pindahan bagi resit daerah lain.");
                return false;}
        }
        return true;
    }

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
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.stripes.hasil.PindaanKutipanActionBean" id="pindaan_kutipan">
<stripes:errors />
<stripes:hidden name="kodNegeri" id="kodNegeri" value="${actionBean.kodNegeri}"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend><div id="l2">Maklumat Urusan</div></legend>
            <!--<p class=instr id="msg1">Sila Pilih Jenis Carian.</p>-->
            <p class=instr id="msg2">Sila Masukkan Nombor Akaun, ID Hakmilik atau Nombor Resit untuk membuat carian.</p>
            <!--<p class=instr id="msg3">Sila Masukkan Nombor Resit atau ID Permohonan untuk membuat carian.</p>-->
<!--                <p>
                    <label>Jenis :</label>
                    <stripes:select name="carian" onchange="javaScript:change(this.value)" id="carian">
                        <stripes:option value="0">Sila Pilih...</stripes:option>
                        <stripes:option value="KUT">Kutipan</stripes:option>
                        <stripes:option value="URN">Urusan</stripes:option>
                    </stripes:select>
                </p>-->

                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <p id="akaun">
                        <label>Nombor Akaun :</label>
                        <stripes:text name="akaun.noAkaun"/>
                    </p>
                </c:if>

                <p id="hakmilik">
                    <label>ID Hakmilik :</label>
                    <stripes:text name="hakmilik.idHakmilik" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');" id="hakmilik"/>
                </p>

                <p id="dokKewangan">
                    <label>Nombor Resit :</label>
                    <stripes:text name="dokumenKewangan.idDokumenKewangan" value ="dokumenKewangan.idDokumenKewangan"/>
                </p>

                <p id="manual">
                    <label>Nombor Resit Kew. 38 :</label>
                    <stripes:text name="resitManual" id="kew38"/>
                </p>

<!--                <p id="permohonan">
                    <label>ID Permohonan :</label>
                    <stripes:text name="permohonan.idPermohonan"/>
                </p>-->

                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="search" value="Cari" class="btn"/>
                    <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('pindaan_kutipan');"/>
                </p>
            <br>
        </fieldset>
    </div>

    <c:if test="${actionBean.visible}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <p class=instr>Sila pilih salah satu daripada hasil carian.</p>
                <p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.resitList}" id="line">
                        <display:column title="Pilih">
                            <div align="center"><stripes:radio name="idHakmilik" value="${line.idDokumenKewangan}" onclick="chooseReceipt(this.value,'${line.cawangan.kod}',${line_rowNum -1})" id="res${row_rowNum - 1}"/>
                            </div>
                        </display:column>
                        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                            <display:column title="Nombor Akaun">${line.akaun.noAkaun}</display:column>
                        </c:if>
                        <display:column title="ID Hakmilik">
                            <c:if test="${actionBean.kodNegeri eq 'melaka'}">${line.akaun.hakmilik.idHakmilik}</c:if>
                            <c:if test="${actionBean.kodNegeri ne 'melaka'}">${line.akaun.noAkaun}</c:if>
                        </display:column>
                        <%--<display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit" />--%>
                        <c:choose>
                            <c:when test="${line.noRujukanManual ne null}">
                                <display:column  title="Nombor Resit Kew.38" >
                                    ${line.noRujukanManual}
                                </display:column>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${actionBean.dokumenKewangan.noRujukan == null}">
                                <display:column  title="Nombor Resit" >
                                    ${line.idDokumenKewangan}
                                </display:column>
                                </c:if>
                                <c:if test="${actionBean.dokumenKewangan.noRujukan != null}">
                                    <display:column  title="Nombor Resit" >
                                    ${line.noRujukan}
                                </display:column>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Bayar" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                        <display:column style="text-align:right" property="amaunBayaran" title="Amaun (RM)" format="{0,number, 0.00}"/>
                    </display:table>
                </div>
                </p>
            </fieldset>
        </div>
        <stripes:text name="" id="resit" style="display:none;"/>
        <stripes:text name="" id="cawResit" style="display:none;"/>
        <stripes:hidden name="${actionBean.pengguna.kodCawangan.kod}" value="${actionBean.pengguna.kodCawangan.kod}" id="pguna"/>
        <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <stripes:submit name="details" value="Seterusnya" disabled="${actionBean.btn}" class="btn" onclick="return validateReceipt(this.form);" id="next"/>
                    <stripes:submit name="kembali" value="Kembali" class="btn"/>
                </td>
            </tr>
        </table></div>
    </c:if>
</stripes:form>
<script language="javascript" >
	$(document).ready(function() {
            $('#next').attr("disabled", "true");    
            <%--$('#hakmilik').hide();
            $('#permohonan').hide();
            $('#dokKewangan').hide();
            $('#manual').hide();
            $('#akaun').hide();
            $('#msg2').hide();
            $('#msg3').hide();
            $('#l2').hide();
            change($('#carian').val());
            dialogInit('Carian Hakmilik');--%>
    });
</script>