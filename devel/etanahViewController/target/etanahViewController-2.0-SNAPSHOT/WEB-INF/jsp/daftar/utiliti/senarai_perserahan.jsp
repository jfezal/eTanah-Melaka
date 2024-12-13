<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        
        $('#hari').hide();
        $('#lebih').hide();    
               
    });          
 

    function changevalue(value){    
                    
        if(value == "Y")
        {
            $('#hari').show();
            $('#lebih').hide();                      
           
        }
        if(value == "N")
        {
            $('#hari').hide();
            $('#lebih').show();           
         
        }
        
       
    }
    
    function viewUrusan(id){
        window.open("${pageContext.request.contextPath}/daftar/kesinambungan?viewUrusanDetail&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700,scrollbars=1");
    }
        function viewUrusan2(id){
        window.open("${pageContext.request.contextPath}/daftar/kesinambungan?viewUrusanDetail2&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700,scrollbars=1");
    }
  
</script>
<s:errors/>
<s:messages/>
<br>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<s:form id="senarai_tugasan" beanclass="etanah.view.daftar.UtilitiSenaraiPerserahan" name="form1">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Senarai Perserahan
            </legend>
            <br>
            <p><label>Pejabat :</label>
                ${actionBean.peng.kodCawangan.name}
                <%--<s:hidden name="kod_caw" value="${actionBean.peng.kodCawangan.kod}"/>--%>
                <%-- <s:select name="kod_caw" value="${actionBean.peng.kodCawangan.kod}">
                     <s:option value="">--Sila Pilih--</s:option>
                     <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                 </s:select>--%>
            </p>

            <p><label>Jabatan :</label>
                <s:select name="kod_jab" style="width:200px;">
                    <s:option value="16">Pendaftaran</s:option>
                    <%--<s:options-collection collection="${listUtil.senaraiKodJabatan}" label="nama" value="kod"/>--%>
                </s:select>
            </p>

            <p><label>Tempoh Perserahan :</label>
                <s:radio name="serah" value="Y" onclick="javaScript:changevalue(this.value)" onchange="clearText('lebih');"></s:radio> Satu Hari Sahaja
                <s:radio name="serah" value="N" onclick="javaScript:changevalue(this.value)" onchange="clearText('hari');"></s:radio> Lebih Dari 1 Hari
                </p>
                <div id="hari">
                    <p><label>Tarikh Perserahan :</label>
                    <s:text name="date_masuk" class="datepicker"
                            onblur="editDateBlur(this, 'DD/MM/YYYY')"
                            onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                            onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                </p>
            </div>
            <div id="lebih">
                <p><label>Tarikh Dari :</label>
                    <s:text name="fromDate" class="datepicker"
                            onblur="editDateBlur(this, 'DD/MM/YYYY')"
                            onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                            onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                </p>
                <p><label>Tarikh Hingga :</label>
                    <s:text name="untilDate" class="datepicker"
                            onblur="editDateBlur(this, 'DD/MM/YYYY')"
                            onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                            onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                </p>
            </div>
            <p><label>Jenis Perserahan :</label>
                <s:select name="kod_serah" style="width:200px;">
                    <%--<s:option value="16">Pendaftaran</s:option>--%>
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodPerserahan}" label="nama" value="kod"/>
                </s:select>
            </p>
            <br>
            <p><label>&nbsp;</label>

                <%-- add onclick event, supaya 'enter key' berfungsi --%>  
                
                <s:submit name="searchAllPermohonan" value="Cari" class="btn" onclick= "doSubmit(this.name,this.form);"/>
                <s:button  name="reset" value="Isi Semula" class="btn" onclick="clearText('senarai_tugasan');"/>
            </p>
            <br>
        </fieldset>
    </div>
    <br>

    <!--Untuk Semua Perserahan : Start -->
    <c:if test="${fn:length(actionBean.senaraiMohonDanCarian) > 0}">
        <div class="subtitle" >
            <fieldset class="aras1">
                <legend>
                    Senarai Perserahan
                </legend>
                <br/>

                <div class="content" align="center">

                    <display:table name="${actionBean.senaraiMohonDanCarian}"  class="tablecloth"  requestURI="/daftar/senaraiPerserahan"
                                   pagesize="15" cellpadding="0" cellspacing="0" id="line">

                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Permohonan">
                            <c:choose>
                                <c:when test="${fn:contains(line.idPermohonan, 'CR') || fn:contains(line.idPermohonan, 'CS')}">
                                    <a href="#" onclick="viewUrusan2('${line.idPermohonan}');return false;">${line.idPermohonan}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="#" onclick="viewUrusan('${line.idPermohonan}');return false;">${line.idPermohonan}</a>
                                </c:otherwise>
                            </c:choose>
                        </display:column>
                        <display:column title="Urusan"  property="urusan"/>
                        <display:column title="Tarikh Perserahan">
                            <fmt:formatDate value="${line.tarikh}" pattern="dd/MM/yyyy hh:mm:ss aa"/>
                        </display:column>
                        <display:column title="Status">
                            <c:if test="${line.status eq 'TS'}">
                                Menunggu Permohonan/Perserahan sebelum untuk bermula
                            </c:if>
                            <c:if test="${line.status eq 'TA'}">
                                Permohonan masih dalam tindakan
                            </c:if>
                            <c:if test="${line.status eq 'AK'}">
                                Urusan ini sedang diproses
                            </c:if>
                            <c:if test="${line.status eq 'TK'}">
                                Urusan telah dibatalkan
                            </c:if>
                            <c:if test="${line.status eq 'GB'}">
                                Urusan ini telah digantung
                            </c:if>
                            <c:if test="${line.status eq 'TP'}">
                                Menunggu tindakan selanjut daripada pemohon untuk meneruskan aliran kerja. Tugasan Berada Di kaunter
                            </c:if>
                            <c:if test="${line.status eq 'SL'}">
                                Urusan ini telah selesai diproses. Keputusan telah dikeluarkan
                            </c:if>
                            <c:if test="${line.status eq 'SS'}">
                                Urusan ini telah disemak semula
                            </c:if>
                            <c:if test="${line.status eq 'T'}">
                                Urusan ini telah ditolak
                            </c:if>
                            <c:if test="${line.status eq 'G'}">
                                Urusan ini telah digantung
                            </c:if>
                            <c:if test="${line.status eq 'D'}">
                                Urusan ini telah didaftarkan
                            </c:if>
                            <c:if test="${line.status eq 'W '}">
                                Urusan ini telah ditarik balik
                            </c:if>
                            <c:if test="${line.status eq '-'}">
                                <div  align="center" > - </div>
                            </c:if>
                        </display:column>
                        <display:column title="Dimasuk Oleh"  property="dimasuk"/>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
    <!--Untuk Semua Perserahan : End -->
    
    <c:if test="${fn:length(actionBean.senaraiPermohonan) > 0 }">
        <div class="subtitle" >
            <fieldset class="aras1">
                <legend>
                    Senarai Perserahan
                </legend>
                <br/>

                <div class="content" align="center">

                    <display:table name="${actionBean.senaraiPermohonan}"  class="tablecloth"  requestURI="/daftar/senaraiPerserahan"
                                   pagesize="15" cellpadding="0" cellspacing="0" id="line">

                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Permohonan">
                            <a href="#" onclick="viewUrusan('${line.idPermohonan}');return false;">${line.idPermohonan}</a>
                        </display:column>
                        <display:column title="Urusan"  property="kodUrusan.nama"/>
                        <display:column title="Tarikh Perserahan">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>
                        </display:column>
                        <display:column title="Status">
                            <c:if test="${line.status eq 'TS'}">
                                Menunggu Permohonan/Perserahan sebelum untuk bermula
                            </c:if>
                            <c:if test="${line.status eq 'TA'}">
                                Permohonan masih dalam tindakan
                            </c:if>
                            <c:if test="${line.status eq 'AK'}">
                                Urusan ini sedang diproses
                            </c:if>
                            <c:if test="${line.status eq 'TK'}">
                                Urusan telah dibatalkan
                            </c:if>
                            <c:if test="${line.status eq 'GB'}">
                                Urusan ini telah digantung
                            </c:if>
                            <c:if test="${line.status eq 'TP'}">
                                Menunggu tindakan selanjut daripada pemohon untuk meneruskan aliran kerja. Tugasan Berada Di kaunter
                            </c:if>
                            <c:if test="${line.status eq 'SL'}">
                                Urusan ini telah selesai diproses. Keputusan telah dikeluarkan
                            </c:if>
                            <c:if test="${line.status eq 'SS'}">
                                Urusan ini telah disemak semula
                            </c:if>
                            <c:if test="${line.status eq 'T'}">
                                Urusan ini telah ditolak
                            </c:if>
                            <c:if test="${line.status eq 'G'}">
                                Urusan ini telah digantung
                            </c:if>
                            <c:if test="${line.status eq 'D'}">
                                Urusan ini telah didaftarkan
                            </c:if>
                            <c:if test="${line.status eq 'W '}">
                                Urusan ini telah ditarik balik
                            </c:if>
                        </display:column>
                        <display:column title="Dimasuk Oleh"  property="infoAudit.dimasukOleh.nama"/>

                    </display:table>
                </div>
            </fieldset>            

        </div>

    </c:if>

    <c:if test= "${fn:length(actionBean.listValue2)>0}">
        <div class="subtitle2" >
            <fieldset class="aras1">
                <legend>
                    Senarai Perserahan
                </legend>
                <br/>

                <div class="content" align="center">
                    <display:table name="${actionBean.listValue2}"  class="tablecloth"  requestURI="/daftar/senaraiPerserahan"
                                   pagesize="15" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Permohonan">
                            <a href="#" onclick="viewUrusan2('${line.permohonanCarian.idCarian}');return false;">${line.permohonanCarian.idCarian}</a>
                        </display:column>
                        <display:column title="Urusan"  property="kodUrusan.nama"/>
                        <display:column title="Tarikh Perserahan">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>
                        </display:column>               
                        <display:column title="Dimasuk Oleh"  property="infoAudit.dimasukOleh.nama"/>
                    </display:table>
                </div>
            </fieldset>            
        </div>
    </c:if>

</s:form>
<br>
