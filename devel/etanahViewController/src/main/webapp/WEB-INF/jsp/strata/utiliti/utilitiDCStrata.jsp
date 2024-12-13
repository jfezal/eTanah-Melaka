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
    <%-- function save(event, f){
         var q = $(f).formSerialize();
         var url = f.action + '?' + event;
         $.post(url,q,
         function(data){
             $('#page_div',opener.document).html(data);
             self.close();
         },'html');
     }--%>
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

         function masuk(){
    <%--var index=document.getElementById('index').value;--%>
    <%--opener.document.getElementById('kod').value = $("#selectedKod").val();--%>
            opener.document.getElementById('nama').value = $("#nama").val();
            self.close();
        }

        function selectRadio(obj){

            alert("radio");
            document.getElementById("selectedKod").value=obj.id;
            document.getElementById("selectedNama").value=obj.value;

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
                    var kodKegunaanPetak = $('#kodKegunaanPetak').val();
    <%--alert(va);--%>
    alert(kodKegunaanPetak);
                    url = '${pageContext.request.contextPath}/utiliti/datacollection?simpanPihak&idPihak=' + va + '&noSkim='+skim + '&kodKegunaanPetak=' +kodKegunaanPetak;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }
            }
        }

        function clearForm(){
            var url = "${pageContext.request.contextPath}/utiliti/datacollection?reset";
            $.post(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.utiliti.UtilityDataCollectionActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
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
                <s:select name="kodKegunaanPetak" id="kodKegunaanPetak">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                </s:select>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="StrbdnUrus" value="Cari" class="btn"/>
                <s:submit name="showForm" value="Isi Semula" class="btn"/>
                <br>
                <%--<s:submit name="mhnbngn" value="Kemaskini Mohon Bangunan" class="btn" onclick="mhnbngn()"/><br>--%>


            </p>
            <%--<p>
                <label>&nbsp;</label>
                <s:submit name="mhnbngn" value="Kemaskini Mohon Bangunan" class="btn" onclick="mhnbngn()"/><br>


            </p>--%>
        </fieldset>
    </div>
    <br>

    <div class="subtitle">


        <fieldset class="aras1">
            <legend>Perbadanan Pengurusan</legend>
            <legend><font color="red"> Jumlah petak strata sebanyak ${fn:length(actionBean.senaraiHakmilik)}. P/S :Sila tunggu sehingga id badan pengurusan updated di table Hakmilik</font></legend>
            <legend>Kegunaan Bangunan : ${actionBean.senaraiHakmilik[0].kodKegunaanBangunan.nama} </legend>
            <p>
                <display:table style="width:100%" class="tablecloth" name="${actionBean.bndUrus}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/utiliti/datacollection" id="line">
                    <display:column title="Id Pihak">${line.pihak.idPihak}</display:column>
                    <display:column title="Nama Pemilik Tanah">${line.nama}</display:column>
                </display:table>

            </p>
        </fieldset>
            
        <c:if test="${actionBean.senaraiPihak != null}" >
            <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>

            <fieldset class="aras1">
                <legend></legend>
                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiPihak}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/utiliti/datacollection" id="line">
                        <display:column>
                            <%--<s:radio name="radio_" id="${line.idPihak}" value="${line.nama}" onclick="javascript:selectRadio(this)"/>--%>
                            <s:radio name="pickpihak" class="pickpihak" id="pickpihak${line_rowNum}" value="${line.idPihak}"/>
                        </display:column>
                        <display:column title="Id Pihak" property="idPihak"/>
                        <display:column title="Nama Perbadanan Pengurusan" property="nama"/>
                    </display:table>

                </p>
                <c:if test="${fn:length(actionBean.senaraiPihak) > 0}" >
                    <p><label>&nbsp;</label>
                        <s:button name="simpanPihak" value="Simpan" id="simpanPihak" class="btn" onclick="pilihPihak(this.name, this.form);"/>
                    </p>
                </c:if>
            </fieldset>

        </c:if>
    </div>
</s:form>