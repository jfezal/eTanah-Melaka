<%-- 
    Document   : partial_kodseksyen
    Created on : Jan 7, 2010, 12:11:37 PM
    Author     : khairil
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function () {
        $('#selectKodSeksyen').val($('#kodSeksyen').val());
    });

    function simpan(f) {
        var kodDaerah = f
        //alert(kodDaerah);
        //var q = $(f).formSerialize();
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });
        $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?simpanPihakKelompok&kodDaerah=' + kodDaerah,
                function (data) {
                    if (data != '') {
                        $('#partialKodBPM').html(data);
                        $.unblockUI();
                    }
                }, 'html');
    }

    function simpanPK(id, idHakmilik) {
        var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?simpanSeksyen&id=' + idHakmilik + '&idHakmilik='+ id;
        $.get(url,
                function (data) {
                    $('#page_div').html(data);
                });
    }
</script>

<s:form partial="true" beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
    <p>
        <label>Seksyen :</label>
        <s:select name="simpanBerkelompokBaru" value="${actionBean.hakmilik.seksyen.kod}" id="selectKodSeksyen" onchange="simpanPK('${actionBean.hakmilik.idHakmilik}', this.value);" style="width:200pt">
            <s:option value="">Sila Pilih</s:option>
            <s:options-collection collection="${actionBean.listKodSeksyenByBpm}" label="nama" value="kod"/>
            <s:hidden name="hakmilik.idHakmilik" id="idHakmilik"/>
        </s:select>
    </p>
</s:form>
