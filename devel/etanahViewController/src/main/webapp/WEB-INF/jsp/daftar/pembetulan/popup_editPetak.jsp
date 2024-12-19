<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
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
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
    input , select {
        width: 95%;
    }
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}

</style>

<script language="javascript">
    $(document).ready(function () {
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        $('input').focus(function () {
            $(this).addClass("focus");
        });

        $('input').blur(function () {
            $(this).removeClass("focus");
        });

        $('select').focus(function () {
            $(this).addClass("focus");
        });

        $('select').blur(function () {
            $(this).removeClass("focus");
        });

    });

    function save(event, f, idH)
    {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idH=' + idH;

        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function showMe(thID) {

        $('#' + thID).toggle();
        $('.' + thID).find(".arrow").toggleClass("up");
    }

    function filterKodGunaTanah() {
        var katTanah = $("#katTanah").val();
        $.post('${pageContext.request.contextPath}/daftar/pembetulan/betul?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
                function (data) {
                    if (data != '') {
                        $('#partialKodGunaTanah').html(data);
                        $.unblockUI();
                    }
                }, 'html');

    }

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function popup(url)
    {
        params = 'width=800px';
        params += ', height=500px';
        params += ', top=0, left=0'
        params += ', fullscreen=yes';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin = window.open(url, 'PopUp', params);
        if (window.focus) {
            newwin.focus()
        }
        return false;
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanSTRActionBean">
    <div id="page_div">
        <s:messages />
        <s:errors />

        <div class="subtitle">

            <fieldset class="aras1">
                <legend>
                    Maklumat Petak
                </legend>
                <p style="color:red">
                    *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

                </p>
                <div align="center">
                    <table class="tablecloth" width="70%">
                        <s:hidden name="idKini" value="idKini" />
                        <c:if test="${!pelan}">
                            Sila masukkan petak aksesori satu - persatu,kemudian klik butan 'Simpan'.
                        </c:if>
                        <c:if test="${pelan}">
                            Sila masukkan pelan aksesori / tambahan satu - persatu, kemudian klik butan 'Simpan'.
                        </c:if>
                        <tr><th colspan="3">ID Hakmilik - ${actionBean.hakmilik.idHakmilik}</th></tr>
                                <s:hidden name = "idH" value ="${actionBean.hakmilik.idHakmilik}"/>
                        <tr><th colspan="3">Butiran Hakmilik</th></tr>
                        <tr><td colspan="3"><table class="tablecloth">
                                    <tr><th>Perkara</th>
                                        <!--<th>Maklumat Baru</th>-->
                                        <th>Maklumat Baru</th></tr>
                                            <c:if test="${!pelan}">
                                        <tr><td>Petak aksesori:</td>
<!--                                            <td class="s">
                                                <c:forEach items="${actionBean.permohonanPihakKemaskiniPetakAksr}" var="i" >
                                                    A${i.nilaiBaru}
                                                </c:forEach>
                                            </td>-->
                                            <td> A<s:text name="petakAksrSTR" value="petakAksrSTR" style="width:70%"/></td>
                                        </tr>
                                        <tr><td>Lokasi Petak aksesori:</td>
                                            <!--<td class="s"></td>-->
                                            <td> <s:text name="lokasiSTR" value="lokasiSTR" style="width:70%;text-transform:uppercase;" /></td>
                                        </tr>
                                        <tr><td>Luas Petak aksesori (m²):</td>
                                            <!--<td class="s"></td>-->
                                            <td> <s:text name="luasPetakAksrSTR" value="luasPetakAksrSTR" style="width:70%"/></td>
                                        </tr>

                            </tr>
                        </c:if>
                        <c:if test="${pelan}">
                            <tr><td>No. Pelan Aksesori / Tambahan:</td>
<!--                                <td class="s">
                                    <c:forEach items="${actionBean.permohonanPihakKemaskiniNoPelan}" var="i" >
                                        PA(B)${i.nilaiBaru}
                                    </c:forEach>
                                </td>-->
                                <td class="s">
                                    PA(B)<s:text name="noPelanSTR" value="noPelanSTR" style="width:70%"/></td>
                            </tr>
                        </c:if>
                    </table> 
                    <tr><td>
                            <div align="center">
                                <s:submit name="saveHmStrPetakAksr" value="Simpan" class="btn" />
                                <s:submit name="kembali" value="Kembali" class="btn"/>
                            </div>
                        </td></tr>
                    </table>
                </div>
            </fieldset>
        </div>
    </div>
</s:form>


