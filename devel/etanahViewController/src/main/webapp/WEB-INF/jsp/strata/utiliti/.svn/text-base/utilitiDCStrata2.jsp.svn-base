<%--
    Document   : DataCollection
    Created on : July 02, 2014, 10:31:32 PM
    Author     : Mudd
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function resetValue() {
        $('#idMohon').val('');
    }

    function doSearch(f,e){
        var v = $('#noSkim').val();
        if(v == ''){
            alert('Sila No Skim');
            $('#noSkim').focus();
            return;
        }
        f.action = f.action + '?' + e;
        f.submit();
    }
    function clearForm(f) {
        $('#noSkim').val('');
    }

    function pilihPihak(event, f){
    <%--alert("pihak");--%>
            var q = $(f).formSerialize();
            var url;
            var len = ${fn:length(actionBean.senaraiPihak)};

            for(var i=1; i<=len; i++){
                if($('#pickpihak'+i).is(':checked')){
                    var va = $('#pickpihak'+i).val();
                    var skim = $('#noSkim').val();
    <%--alert(va);--%>
    <%--alert(skim);--%>
                    url = '${pageContext.request.contextPath}/utiliti/datacollection?simpanPihak&idPihak=' + va + '&noSkim='+skim;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }
            }
        }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.utiliti.UtilityDataCollectionActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="dokumen">

        <fieldset class="aras1">
            <legend>
                Carian Perbadanan Pengurusan
            </legend>
            <s:hidden id="selectedKod" name="selectedKod" />
            <s:hidden id="selectedNama" name="selectedNama" />

            <p>
                <label>No Skim :</label>
                <s:text name="noSkim" size="34" maxlength="20" id="noSkim" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <p>

                <label>Nama :</label>
                <s:text name="nama" id="nama" size="100"/>
            </p>
            <p>
                <label>Kod Guna Petak :</label>
                <s:select name="kodKegunaanPetak">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                </s:select>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="StrbdnUrus" value="Cari" class="btn"/>
                <s:submit name="showForm" value="Isi Semula" class="btn"/>
            </p>
<%--<c:if test="${fn:length(actionBean.folderDokumenSebelum.senaraiKandungan) > 0}">--%>
            <c:if test="${actionBean.noSkim ne null}">
                <%--${actionBean.senaraipermohonanBangunan}--%>
                <c:if test="${fn:length(actionBean.senaraipermohonanBangunan) == 0}">
                    <legend>Kegunaan Bangunan : ${actionBean.senaraiHakmilik[0].kodKegunaanBangunan.nama} </legend>
                    <font color="red">Sila pilih kegunaan petak berdasarkan kegunaan bangunan dan klik button Kemaskini</font>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="mhnbngn" value="Kemaskini" class="btn" onclick="mhnbngn()"/><br>
                    </p>
                </c:if>
            </c:if>
        </fieldset>
    </div>
</s:form>


