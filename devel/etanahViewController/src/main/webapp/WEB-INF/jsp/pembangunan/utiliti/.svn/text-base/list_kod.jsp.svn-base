<%-- 
    Document   : list_kod
    Created on : Aug 1, 2013, 12:23:43 PM
    Author     : khairul.hazwan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript">
    function confirmRefresh() {
        setTimeout("location.reload(true);", 200);
    }

    function refreshDocument() {
        document.location = document.location;
    }

    function testing(a) {    
        var url = '${pageContext.request.contextPath}/pembangunan/utiliti/kodlist?editData&no=' + a;
        //alert(url);
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
    function saveBetul2() {
        var size = $("#money").val();
        var name = $("#nameTable").val();
        var item = new Array();
        var a;
        a = $("#data" + 1).val();
        for (var i = 1; i <= size; i++) {
            a = $("#data" + i).val();
            item[i - 1] = "'" + a + "'";
        }

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pembangunan/utiliti/kodlist?addData&item=' + item + '&tableName=' + name;
            $.ajax({
                type: "GET",
                url: url,
                success: function(data) {
                    $('#page_div', opener.document).html(data);
                    self.close();
                }
            });
        }
    }
    function tambah() {
        var ntab = $("#nameTable").val();
        var url = '${pageContext.request.contextPath}/pembangunan/utiliti/kodlist?addKod&ntab=' +ntab;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
        function(data) {
            $('#page_div').html(data);
            opener.pilihKod();
        }, 'html');
    }
   
    
    function edit(kod) {       
        var ntab = $("#nameTable").val();       
        var url = '${pageContext.request.contextPath}/pembangunan/utiliti/kodlist?edit&kod=' + kod + '&ntab='
            + ntab;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
    function kemaskini(kdskod){       
        var ntab = $("#nameTable").val();       
        var url = '${pageContext.request.contextPath}/pembangunan/utiliti/kodlist?edit&kdskod=' + kdskod + '&ntab='
            + ntab;                
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
    
    function validateForm() {

                var kod = document.form1.kod.value;

                if ((kod == ""))
                {
                    alert('Sila Pilih Kod ');
                    document.form1.kod.focus();
                    return false
                }
                return true;

    }
    
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.utiliti.ListKodUtil" >
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <div align="center">
                <c:if test="${actionBean.rowData eq false}">
                    <legend>Senarai Kod-kod dalam Pangkalan Data</legend>
                    <br>
                    <p>
                        <s:select name="nameTable" id="" value="nameTable" style="width:246px">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="ADUN">ADUN</s:option>
                            <s:option value="KOD_AGENSI">JABATAN TEKNIKAL</s:option>
                            <s:option value="KOD_DUN">DUN</s:option>
                            <s:option value="KOD_KWS_PARLIMEN">PARLIMEN</s:option>
                        </s:select>
                    </p>
                    <p>
                        <s:submit name="pilihKod" value="Pilih" class="btn"/>
                    </p>

                </c:if>
            </div>

            <c:if test="${actionBean.rowData eq true}">
                <s:hidden name="nameTable" id="nameTable"/>

                <c:if test="${actionBean.nameTable eq 'ADUN'}">
                    <legend align="center">ADUN</legend>
                </c:if> 
                <c:if test="${actionBean.nameTable eq 'KOD_AGENSI'}">
                    <legend align="center">JABATAN TEKNIKAL</legend>
                </c:if>
                <c:if test="${actionBean.nameTable eq 'KOD_DUN'}">
                    <legend align="center">DUN</legend>
                </c:if>
                <c:if test="${actionBean.nameTable eq 'KOD_KWS_PARLIMEN'}">
                    <legend align="center">PARLIMEN</legend>
                </c:if>
                <div align="center">
                    <p>
                        <s:button name="tambahData" id="tambahData" value="Tambah" class="btn" onclick="tambah();"/>
                        <s:submit name="showForm" value="Kembali" class="btn"/>                      
                    </p>         
                    <br><br>
                </div>

                <c:if test="${actionBean.nameTable eq 'ADUN' || actionBean.nameTable eq 'KOD_AGENSI'}">
                    <center>
                        <display:table class="tablecloth" name="${actionBean.kodAgensi}" pagesize="10" requestURI="/pembangunan/utiliti/kodlist" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Bil" style="text-align:center">${line_rowNum}</display:column>
                            <display:column title="KOD" style="text-align:center">${line.kod}</display:column>
                            <%--<display:column title="KEMENTERIAN" style="text-align:center">${line.kodKementerian}</display:column>--%>
                            <display:column title="AKTIF" style="text-align:center">${line.aktif}</display:column>
                            <display:column title="DAERAH" style="text-align:center">${line.kodDaerah.nama}</display:column>
                            <display:column title="GELARAN" style="text-align:center">${line.kodGelaran.nama}</display:column>
                            <display:column title="NAMA" style="text-align:center">${line.nama}</display:column>
                            <display:column title="ALAMAT1" style="text-align:center">${line.alamat1}</display:column>
                            <display:column title="ALAMAT2" style="text-align:center">${line.alamat2}</display:column>
                            <display:column title="ALAMAT3" style="text-align:center">${line.alamat3}</display:column>
                            <display:column title="ALAMAT4" style="text-align:center">${line.alamat4}</display:column>
                            <display:column title="NEGERI" style="text-align:center">${line.kodNegeri.nama}</display:column>
                            <display:column title="POSKOD" style="text-align:center">${line.poskod}</display:column>                        
                            <display:column title="EMEL" style="text-align:center">${line.emel}</display:column>
                            <display:column title="NO.TEL1" style="text-align:center">${line.noTelefon1}</display:column>
                            <display:column title="NO.TEL2" style="text-align:center">${line.noTelefon2}</display:column>
                            <display:column title="KEMASKINI" style="text-align:center">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="edit(${line.kod});"  onmouseover="this.style.cursor = 'pointer';">
                                </p>
                            </display:column>
                        </display:table>
                    </center>
                </c:if>
                <c:if test="${actionBean.nameTable eq 'KOD_DUN'}">
                    <center>
                        <display:table class="tablecloth" name="${actionBean.kodDun}" pagesize="10" requestURI="/pembangunan/utiliti/kodlist" cellpadding="0" cellspacing="0" id="line">                       
                            <display:column title="Bil" style="text-align:center">${line_rowNum}</display:column>
                            <display:column title="KOD" style="text-align:center">${line.kod}</display:column>                        
                            <display:column title="NAMA" style="text-align:center">${line.nama}</display:column>
                            <display:column title="DIMASUK" style="text-align:center">${line.infoAudit.dimasukOleh.nama}</display:column>
                            <display:column title="TRH_MASUK" style="text-align:center">${line.infoAudit.tarikhMasuk}</display:column>
                            <display:column title="DIKKINI" style="text-align:center">${line.infoAudit.dikemaskiniOleh.nama}</display:column>
                            <display:column title="TRH_KKINI" style="text-align:center">${line.infoAudit.tarikhKemaskini}</display:column>
                            <display:column title="KOD_AGENSI" style="text-align:center">${line.kodAgensi.nama}</display:column>   
                            <display:column title="KOD_KWS_PARLIMEN" style="text-align:center">${line.kodKawasanParlimen.nama}</display:column>   
                            <display:column title="KEMASKINI" style="text-align:center">
                                <p align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="kemaskini('${line.kod}')"  onmouseover="this.style.cursor = 'pointer';">
                                </p>
                            </display:column>
                        </display:table>
                    </center>
                </c:if>             

                <c:if test="${actionBean.nameTable eq 'KOD_KWS_PARLIMEN'}">
                    <center>
                        <display:table class="tablecloth" name="${actionBean.kodParimen}" pagesize="10" requestURI="/pembangunan/utiliti/kodlist" cellpadding="0" cellspacing="0" id="line">                        
                            <display:column title="Bil" style="text-align:center">${line_rowNum}</display:column>
                            <display:column title="KOD" style="text-align:center">${line.kod}</display:column>                        
                            <display:column title="NAMA" style="text-align:center">${line.nama}</display:column>
                            <display:column title="DIMASUK" style="text-align:center">${line.infoAudit.dimasukOleh.nama}</display:column>
                            <display:column title="TRH_MASUK" style="text-align:center">${line.infoAudit.tarikhMasuk}</display:column>
                            <display:column title="DIKKINI" style="text-align:center">${line.infoAudit.dikemaskiniOleh.nama}</display:column>
                            <display:column title="TRH_KKINI" style="text-align:center">${line.infoAudit.tarikhKemaskini}</display:column>
                            <display:column title="KEMASKINI" style="text-align:center">
                                <p align="center">                               
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onclick="kemaskini('${line.kod}')"  onmouseover="this.style.cursor = 'pointer';">
                                </p>
                            </display:column>
                        </display:table>
                    </center>
                </c:if>               
            </c:if>
        </fieldset>
    </div>
</s:form>