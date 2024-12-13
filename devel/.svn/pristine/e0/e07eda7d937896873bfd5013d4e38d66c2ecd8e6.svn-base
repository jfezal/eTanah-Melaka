<%-- 
    Document   : carian_idKumpulan
    Created on : Aug 26, 2010, 1:58:38 PM
    Author     : abdul.hakim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div id="cr">
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Carian ID Kumpulan</font>
            </div>
        </td>
    </tr>
</table></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<s:form beanclass="etanah.view.stripes.hasil.SearchingIDKumpulanActionBean" id="carian_idKump">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <p>
                <label>Jenis Carian :</label>
                <s:select name="carian" onchange="javaScript:changeCarian(this.value)" id="carian">
                    <s:option value="0">Sila Pilih...</s:option>
                    <s:option value="KMP">ID Kumpulan</s:option>
                    <s:option value="HMK">ID Hakmilik</s:option>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <s:option value="AKN">Nombor Akaun</s:option>
                    </c:if>
                </s:select>
            </p>

            <div id="kumpulan">
                <p>
                    <label>ID Kumpulan :</label>
                    <s:text name="idKumpulan" onkeyup="this.value=this.value.toUpperCase();" id="kump"/>
                </p>

                <p>
                    <label>Nama Kumpulan :</label>
                    <s:text name="namaKumpulan" onkeyup="this.value=this.value.toUpperCase();" id="kump"/>
                </p>

                <p>
                    <label>Pilih Daerah :</label>
                    <s:select name="daerah" id="daerah">
                        <s:option label="Pilih Daerah ..." value="" />
                        <c:forEach items="${actionBean.senaraiDaerah}" var="i" >
                            <s:option value="${i.kod}">${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>
            </div>

            <p id="hakmilik">
                <label>ID Hakmilik :</label>
                <s:text name="hakmilik.idHakmilik" onkeyup="this.value=this.value.toUpperCase();" id="hm"/>
            </p>

            <p id="akaun">
                <label>Nombor Akaun :</label>
                <s:text name="akaun.noAkaun" onkeyup="this.value=this.value.toUpperCase();" id="ak"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="search" value="Cari" class="btn"/>
                <s:submit name="tambahKumpulan" value="Tambah Kumpulan" class="longbtn"/>
                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('carian_idKump');"/>
            </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <c:if test="${actionBean.flag1}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKumpulanAkaun}" id="line">
                        <display:column title="Bil.">
                            <div align="center">${line_rowNum}.</div>
                        </display:column>
                        <display:column property="idKumpulan" title="ID Kumpulan"/>
                        <display:column property="cawangan.daerah.nama" title="Daerah"/>
                        <display:column property="catatan" title="Nama Kumpulan"/>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Masuk" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                        <display:column title="Bil. Hakmilik" style="text-align:center">
                            <c:set value="${fn:length(line.senaraiAkaun)}" var="count"/>
                            <c:out value="${count}"/> Hakmilik
                        </display:column>
                        <display:column title="Papar">
                            <s:button name="" class="btn" value="Papar" onclick="popup('${line.idKumpulan}',this.form)"/>
                        </display:column>
                        <display:column title="Hapus Kumpulan" style="width:90px;">
                            <c:choose>
                                <c:when test="${actionBean.pgunaCaw.kod eq line.cawangan.daerah.kod}">
                                    <div align="center"><a href="#">
                                        <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                             onclick="hapusKumpulan('${line.idKumpulan}');"/></a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div align="center">
                                        <img alt='Tidak Dibenarkan Hapus' src='${pageContext.request.contextPath}/images/lock.gif' />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.flag2}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKumpulan}" id="line">
                        <display:column title="Bil.">
                            <div align="center">${line_rowNum}.</div>
                        </display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="kumpulan.idKumpulan" title="ID Kumpulan"/>
                        <display:column property="kumpulan.cawangan.daerah.nama" title="Cawangan"/>
                        <display:column property="kumpulan.catatan" title="Nama Kumpulan"/>
                        <display:column property="kumpulan.infoAudit.tarikhMasuk" title="Tarikh Masuk" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                        <display:column title="Hapus ID Hakmilik">
                            <c:choose>
                                <c:when test="${actionBean.pgunaCaw.kod eq line.cawangan.daerah.kod}">
                                    <div align="center"><a href="#">
                                        <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                         onclick="hapusHakmilik('${line.hakmilik.idHakmilik}','${line.kumpulan.idKumpulan}');" /></a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div align="center">
                                        <img alt='Tidak Dibenarkan Hapus' src='${pageContext.request.contextPath}/images/lock.gif' />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <%--<div align="center"><a href="#">
                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                         onclick="hapusHakmilik('${line.hakmilik.idHakmilik}','${line.kumpulan.idKumpulan}');" /></a>
                            </div>--%>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>
</div>
<script language="javascript" type="text/javascript">
	$(document).ready(function() {
            $('#hakmilik').hide();
            $('#kumpulan').hide();
            $('#akaun').hide();
            changeCarian($('#carian').val());
    });
</script>
<script type="text/javascript">
    function changeCarian(f){
        if (f == 'KMP'){
            $('#kumpulan').show();
            $('#hakmilik').hide();
            $('#akaun').hide();
            $('#ak').val("");
            $('#hm').val("")
        }
        else if (f == 'HMK'){
            $('#hakmilik').show();
            $('#kumpulan').hide();
            $('#akaun').hide();
            $('#kump').val("");
            $('#ak').val("");
        }
        else if (f == 'AKN'){
            $('#akaun').show();
            $('#hakmilik').hide();
            $('#kumpulan').hide();
            $('#kump').val("");
            $('#hm').val("");
        }
        else{
            $('#hakmilik').hide();
            $('#kumpulan').hide();
            $('#akaun').hide();
            $('#kump').val("");
            $('#ak').val("");
            $('#hm').val("");
        }
    }

    function popup(id,f){
        var q = $(f).formSerialize();
        f.action = f.action + '?popup&idKumpulan=' + id + '&popup&' + q;
        f.submit();
    }

    function hapusHakmilik(id, kump){
        if(confirm("Adakah anda pasti untuk hapuskan ID Hakmilik "+id+" dari Kumpulan "+kump+" ?")){
            var url = '${pageContext.request.contextPath}/hasil/carian_idKumpulan?delete&idHakmilik='+id;
            $.get(url,
            function(data){
                $('#cr').html(data);
                alert("Maklumat Berjaya Dikemaskini");
            },'html');
        }
    }

    function entsub1() {
        document.getElementById("delete").click();
        return true;
    }

    function hapusKumpulan(id){
        if(confirm("Adakah anda pasti untuk hapuskan ID Kumpulan "+id+"?")){
            <%--entsub1();--%>
            var url = '${pageContext.request.contextPath}/hasil/carian_idKumpulan?deleteKumpulan&idKumpulan='+id;
            $.get(url,
            function(data){
                $('#cr').html(data);
                alert("ID Kumpulan "+id+" telah berjaya dihapuskan.");
            },'html');
        }
    }

    function refreshCarian(cari){
        var url = '${pageContext.request.contextPath}/hasil/carian_idKumpulan?reloadPage&idCarian='+cari;
        $.get(url,
        function(data){
            $('#cr').html(data);
        }, 'html');
    }

</script>