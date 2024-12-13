<%-- 
    Document   : notis_gantian_edit
    Created on : Dec 28, 2009, 1:21:26 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

    function dateValidation(value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#datepicker').val("");
        }
    }

    function filterJawatan(f,kodJawatan){
        var queryString = $(f).formSerialize();
        var url = "${pageContext.request.contextPath}/hasil/notis_gantian?filterByJawatan&"+queryString+"&kodJawatan="+kodJawatan;
        $.get(url,
        function(data){
            $('#jaw').html(data);
        },'html');
    }
</script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        vertical-align:top;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:34em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        text-align:justify;
        float:left;
        width:30em;
    }
</style>
<div id="jaw">
    <s:messages/>
    <s:form beanclass="etanah.view.stripes.hasil.NotisGantianActionBean">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Hakmilik</legend>
                <p>
                    <label>Nama :</label>
                    ${actionBean.dasarTuntutanNotis.pihak.nama}&nbsp;
                </p>
                <p>
                    <label>Alamat :</label>
                    ${actionBean.dasarTuntutanNotis.pihak.suratAlamat1}&nbsp;
                </p>
                <c:if test="${actionBean.dasarTuntutanNotis.pihak.suratAlamat2 ne null}">
                    <p>
                        <label>&nbsp;</label>
                        ${actionBean.dasarTuntutanNotis.pihak.suratAlamat2}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.dasarTuntutanNotis.pihak.suratAlamat3 ne null}">
                    <p>
                        <label>&nbsp;</label>
                        ${actionBean.dasarTuntutanNotis.pihak.suratAlamat3}&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.dasarTuntutanNotis.pihak.suratAlamat4 ne null}">
                    <p>
                        <label>&nbsp;</label>
                        ${actionBean.dasarTuntutanNotis.pihak.suratAlamat4}&nbsp;
                    </p>
                </c:if>
                <p>
                    <label>&nbsp;</label>
                    ${actionBean.dasarTuntutanNotis.pihak.suratPoskod}&nbsp;${actionBean.dasarTuntutanNotis.pihak.suratNegeri.nama}
                </p>
                <br>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Surat Akuan</legend>
                <p>
                    <label>Tarikh Surat Akuan :</label>
                    <s:format value="${actionBean.dasarTuntutanNotis.tarikhNotis}" formatPattern="dd/MM/yyyy"/>&nbsp;
                </p>
                <p>
                    <label>Sebab Dikembalikan :</label>
                    <table border="0">
                        <tr>
                            <td id="tdDisplay"><s:format value="${actionBean.dasarTuntutanNotis.catatanTerima}"/>&nbsp;</td>
                        </tr>
                    </table>
                </p>
                <%--<table border="0" align="center">
                    <tr>
                        <td id="tdLabel">Tarikh Surat Akuan :</td>
                        <td id="tdDisplay"><s:format value="${actionBean.dasarTuntutanNotis.tarikhNotis}" formatPattern="dd/MM/yyyy"/>&nbsp;</td>
                    </tr>
                    <tr>
                        <td id="tdLabel">Sebab Dikembalikan :</td>
                        <td id="tdDisplay"><s:format value="${actionBean.dasarTuntutanNotis.catatanTerima}"/>&nbsp;</td>
                    </tr>
                </table>--%>
                <br><br>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perintah</legend>
                <p>
                    <label><em>*</em>Tarikh Perintah Dikeluarkan :</label>
                    <s:text name="tarikhPerintah" class="datepicker" readonly="true"/>
                </p>
                <p>
                    <label><em>*</em>Jawatan :</label>
                    <s:select name="jawatan" id="kodJawatan" onchange="filterJawatan(this.form, this.value);">
                        <s:option label="Pilih Jawatan..." value="0" />
                        <s:options-collection collection="${actionBean.senaraiJawatan}" label="nama" value="kod" sort="nama"/>
                    </s:select>
                </p>

                <p>
                    <label><em>*</em>Nama :</label>
                    <s:select name="nama" id="nama" onchange="javascript:updateKod(0);" style="width:500px;">
                        <s:option label="Pilih Nama..."  value="0" />
                        <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" sort="nama"/>
                    </s:select>
                </p>
                <br><br>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perintah Penyampaian</legend>
                <p>Penyampaian Gantian di bawah <b>Seksyen 432(1) Kanun Tanah Negara</b> secara :</p>
                <br>
                <p>a) dengan menampal sesalinan notis ditempat yang boleh dilihat</p>
                <p>
                    <label>i) diatas tanah berkenaan :</label>
                    <b>
                        ${actionBean.hakmilik.lot.nama}&nbsp;${actionBean.hakmilik.noLot}&nbsp;
                        ${actionBean.hakmilik.kodHakmilik.kod}&nbsp;${actionBean.hakmilik.noHakmilik}</b>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <b>${actionBean.hakmilik.bandarPekanMukim.nama}</b>
                </p>
                <%--<p>
                    <label>ii) Nombor Lot / Jenis Hakmilik :</label>
                    <b>${actionBean.hakmilik.lot.nama}&nbsp;${actionBean.hakmilik.noLot}</b>
                </p>--%>
                <p>
                    <label><em>*</em>ii) di papan kenyataan bangunan Mahkamah  :</label>
                    <s:text name="dasarTuntutanNotis.tempatHantar1" />
                </p>
                <p>
                    <label><em>*</em>iii) di papan kenyataan Masjid / Rumah Ibadat / </label>
                    &nbsp;
                </p>
                <p>
                    <label>Balai Penghulu / Balai Raya :</label>
                    <s:text name="dasarTuntutanNotis.tempatHantar2" />
                </p>
                <p>
                    <label>iv) di Pasar / tempat awam :</label>
                    <s:text name="dasarTuntutanNotis.tempatHantar3" />
                </p>
                <p>
                    <label>v) di Papan Kenyataan Pejabat Tanah  :</label>
                    <s:text name="dasarTuntutanNotis.tempatHantar4" />
                </p>
                <%--<p>
                    <label>vi) di Pasar   :</label>
                    <s:text name="dasarTuntutanNotis.tempatHantar5" />
                </p>
                <p>
                    <label>viii) di tempat Awam  :</label>
                    <s:text name="dasarTuntutanNotis.tempatHantar6" />
                </p>--%>
                <p>
                    <label></label>
                    &nbsp;
                </p>
                <c:if test="${actionBean.flag}">
                    <p>
                        b) dengan menyiarkan satu salinan notis di dalam Warta Kerajaan Negeri Sembilan berhubung
                        dengan notis 6A
                    </p>
                    <p>
                        <label>Nombor Warta :</label>
                        <s:text name="dasarTuntutanNotis.noRujukan" />
                    </p>
                    <p>
                        <label>Tarikh Warta :</label>
                        <s:text name="tarikhWarta" id="datepicker" size="20" maxlength="10" class="datepicker" onchange="dateValidation(this.value)"/>
                    </p>
                </c:if>
            </fieldset>
        </div>
        <table width="100%" bgcolor="green">
            <tr>
                <td align="right">
                    <s:submit name="save" value="Simpan" class="btn" />&nbsp;
                    <s:reset name="" value="Isi Semula" class="btn" />&nbsp;
                    <s:button name="" value="Tutup" onclick="self.close()" class="btn" />
                </td>
            </tr>
        </table>

    </s:form>
</div>


