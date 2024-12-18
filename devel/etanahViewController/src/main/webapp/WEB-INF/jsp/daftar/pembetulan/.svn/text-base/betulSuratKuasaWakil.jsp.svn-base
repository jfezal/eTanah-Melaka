<%--
    Document   : betulSuratKuasaWakil
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>
<script type="text/javascript">
 $(document).ready( function(){
           $('.noRujukan').each(function(index){
            $(this).blur(function(){
                validateSurat(index+1);
            });
        });
     });

      function validateSurat(idxHakmilik){
        var val = $("#noRujukan" + idxHakmilik).val();
        frm = this.form;
        if (val == null || val == "") return;
        val = val.toUpperCase();
        if (!doCheckSurat (val)) {
            $("#noRujukan" + idxHakmilik).val("");
            alert ('No Suratkuasa Wakil sudah dipilih.');
            return;
        }

         $.get("${pageContext.request.contextPath}/daftar/pembetulan/SuratKuasaWakil?doCheckSurat&noRujukan=" + val,
        function(data){
            if(data == '3'){
                $("#msg" + idxHakmilik).html("OK");
            } else if(data =='0'){
                $("#noRujukan" + idxHakmilik).val("");
                $("#msg" + idxHakmilik).html("<em>No Suratkuasa Wakil " + val + " tidak wujud!</em>");
            } else if(data =='1'){
                $("#noRujukan" + idxHakmilik).val("");
                $("#msg" + idxHakmilik).html("<em>No Suratkuasa Wakil " + val + " bukan dari cawangan ini!</em>");
            } else if (data == '2'){
                $("#noRujukan" + idxHakmilik).val("");
                $("#msg" + idxHakmilik).html("<em>No Suratkuasa Wakil " + val + " ini tidak Aktif!<em>");
            } 
        });
}

   function doCheckSurat (noRujukan) {
        var valid = true;
        $('.noRujukanlama').each(function(index){
            if (noRujukan.trim() == $(this).val()) {
                valid = false;
            }
        });
        return valid;
    }

        
        function serah(){
            var len = $('.nama').length;
            var param = '';

            for(var i=1; i<=len; i++){
                var ckd = $('#chkbox'+i).is(":checked");
                if(ckd){
                    param = param + '&idUrusan=' + $('#chkbox'+i).val();
                }
            }
            if(param == ''){
                alert('Tiada Urusan.');
                return;
            }
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/SuratKuasaWakil?savePerserahan'+param;

            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    alert("error=" + xhr.responseText);
                },
                success : function(data){
                    $('#page_div').html(data);
                }
            });
        }

        function removeChanges(val){
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer){
                var url = '${pageContext.request.contextPath}/daftar/pembetulan/SuratKuasaWakil?deleteChanges&idUrusan='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                });
            }
        }
        
        function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/SuratKuasaWakil?maklumatSuratKuasaWakil&idHakmilik=' + val;        
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.BetulSuratKuasaWakilActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Pembetulan No. Surat Kuasa Wakil Ke Atas Perserahan
            </legend>
            <br>
            <p>
                <label>Hakmilik :</label>
                <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.hakmilikPermohonanList}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>
            <br>
            <div class="content" align="center">
                Sila Klik pada ID Perserahan untuk pembetulan No Surat Kuasa Wakil
                <display:table class="tablecloth" name="${actionBean.hakmilikUrusanListByKodserah}" cellpadding="0" cellspacing="0" id="line">
                    <display:column>
                        <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.idUrusan}"/>
                    </display:column>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idPerserahan" title="ID Perserahan" class="nama" />
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="kodUrusan.kod" title="Kod Urusan" />
                    <display:column property="kodUrusan.nama" title="Urusan" />
                    <display:column property="tarikhDaftar" title="Tarikh Daftar" format="{0,date,dd-MM-yyyy}"/>
                </display:table>
            </div>
            <c:if test="${fn:length(actionBean.hakmilikUrusanListByKodserah) > 0}">
            <p>
                <label>&nbsp;</label>
                <s:button name="savePerserahan" value="Pilih" class="btn" onclick="serah();"/>
            </p>
            </c:if>
            <br/>
            <c:if test="${fn:length(actionBean.mohonAtasUrusan) > 0}">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.mohonAtasUrusan}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="urusan.kodUrusan.nama" title="Nama Urusan" sortable="true"/>
                        <display:column property="urusan.idPerserahan" title="ID Perserahan" />
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Permohonan" format="{0,date,dd-MM-yyyy}"/>
                       <display:column  title="No SuratKuasa"> 
                           <%--${actionBean.noSurat}--%>
                           <c:forEach items="${line.urusan.senaraiSurat}" var="items">                               
                               <c:if test="${fn:startsWith(items.kodDokumen.kod, 'SW')}">
                                   ${items.noSurat}<br/>
                               </c:if>
                               <s:hidden name="noRujukanlama" id="noRujukanlama${line3.count}" value="${items.noSurat}" class="noRujukanlama"/>
                           </c:forEach>
                       </display:column>
                       
                        <display:column title="No SuratKuasa Wakil Baru">
                            <c:forEach items="${line.urusan.senaraiSurat}" var="items2" varStatus="line3">                                
                               <c:if test="${fn:startsWith(items2.kodDokumen.kod, 'SW')}">
                                  <s:text name="noRujukan[${line3.count-1}]" id="noRujukan${line3.count}" class="noRujukan"/><br/>
                               </c:if>
                                <div id="msg${line3.count}"/>
                           </c:forEach> 
                        </display:column>
                        
                          <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem${line_rowNum}' onclick="removeChanges('${line.urusan.idUrusan}')" onmouseover="this.style.cursor='pointer';" >
                            </div>
                        </display:column>
                      
                    </display:table>
                </div>
                <br>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="saveSurat" value="Simpan" class="btn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>
                </p>
                <br/>
            </c:if>
        </fieldset>
    </div>   
</s:form>
