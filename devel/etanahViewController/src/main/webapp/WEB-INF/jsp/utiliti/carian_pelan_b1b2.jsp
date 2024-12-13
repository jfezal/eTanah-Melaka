<%-- 
    Document   : kemasukan_pelan_manual
    Created on : Dec 17, 2014, 4:47:50 PM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function cc(a, f) {
        var form = $(f).formSerialize();
//        alert(a);

        var daerah;
        var url = '${pageContext.request.contextPath}/utiliti/carian_pelan_path?filterMukim&daerah=' + a + '&';
        window.location = url + form;
    }

    function seksyenfilter(a, f) {
        var form = $(f).formSerialize();
//        alert(a);

        var daerah;
        var url = '${pageContext.request.contextPath}/utiliti/carian_pelan_path?filterSeksyen&mukim=' + a + '&';
        window.location = url + form;
    }

    function doPopup(daerah, mukim, seksyen, noLot, jenispelan) {
        // alert(daerah+ seksyen+ mukim+ noLot+ jenispelan);
        var url = '${pageContext.request.contextPath}/utiliti/carian_pelan_path?viewPelan&daerah=' + daerah + '&seksyen=' + seksyen + '&mukim=' + mukim + '&noLot=' + noLot + '&jenispelan=' + jenispelan;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");
    }






</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:form beanclass="etanah.view.utility.CarianPelanB1B2ActionBean" name="carianPelan" id="carianPelan">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
        <div class="content">
            <fieldset class ="aras1">
                <legend>Carian Permohonan</legend>
                <p>
                    <label>Jenis Pelan :</label>
                    <s:select name="jenispelan" id="jenispelan">
                        <s:option value="B1">B1</s:option>
                        <s:option value="B2">B2</s:option>
                    </s:select>
                </p>
                <p>
                    <label>Daerah :</label>
                    <s:select name="daerah" id="Daerah"  onchange="cc(this.value,this.form);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listKodDaerah}" value="kod" label="nama" />                    
                    </s:select>
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <s:select name="mukim" onchange="seksyenfilter(this.value,this.form);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listBPM}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <p><label><font color="red">*</font>Seksyen:</label>
                        <s:select name="seksyen">
                            <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listKodSeksyen}" value="kod" label="nama"/>
                    </s:select>
                </p>
                <p><label><font color="red">*</font>Lot/No PT:</label>
                        <s:text id="idPermohonan" name="noLot"></s:text>
                    </p>



                    <p align="center"><s:submit name="cari" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('carianPelan')" /></p>

            </fieldset>
        </div>

    </div> 

    <div class ="subtitle">
        <div class="content">
            <fieldset class ="aras1">
                <legend>Rekod</legend>
                <fieldset class="aras1">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listpelanGIS}" pagesize="20" cellpadding="0" cellspacing="0" id="line">


                            <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>


                            <display:column  title="Daerah" style="vertical-align:baseline"> ${line.kodDaerah.kod}
                                <input type="hidden" name="kodDaerahDel" value="${line.kodDaerah.kod}">
                                <input type="hidden" name="kodDaerahDel" value="${line.kodDaerah.kod}">
                                <input type="hidden" name="kodMukimDel" value="${line.kodMukim}">
                                <input type="hidden" name="kodSeksyenDel" value="${line.kodSeksyen}">
                                <input type="hidden" name="noLotDel" value="${line.noLot}">
                                <input type="hidden" name="jenisPelanDel" value="${line.jenisPelan}">
                            </display:column>
                            <display:column  title="Mukim" style="vertical-align:baseline"> ${line.kodMukim}
                            </display:column>  <display:column  title="Seksyen" style="vertical-align:baseline"> ${line.kodSeksyen}
                            </display:column>  
                            <display:column  title="Jenis Pelan" style="vertical-align:baseline"> ${line.jenisPelan}
                            </display:column>
                            <display:column  title="Tarikh Kemaskini" style="vertical-align:baseline"> ${line.trhKemaskini}
                            </display:column>
                            <display:column  title="No Lot" style="vertical-align:baseline"> ${line.noLot}
                            </display:column>

                            <display:column  title="Luas" style="vertical-align:baseline"> ${line.luas}
                            </display:column>
                            <display:column  title="Unit" style="vertical-align:baseline"> ${line.unitUkur}
                            </display:column>
                            <display:column  title="No PA" style="vertical-align:baseline"> ${line.noPelanAkui}
                            </display:column>
                            <display:column  title="No Fail Ukur" style="vertical-align:baseline"> ${line.noFailUkur}
                            </display:column>
                            <display:column  title="PATH" style="vertical-align:baseline"> 
                                <a href="#" onclick="doPopup('${line.kodDaerah.kod}', '${line.kodMukim}', '${line.kodSeksyen}', '${line.noLot}', '${line.jenisPelan}')"><c:if test="${line.jenisPelan eq 'B1'}">${actionBean.b1path}</c:if><c:if test="${line.jenisPelan eq 'B2'}">${actionBean.b2path}</c:if>${line.pelanTif}</a>
                            </display:column>
                                
                                <s:hidden name="kodDaerahDel" value="${line.kodDaerah.kod}"/>
                                <s:hidden name="jenisPelanDel" value="${line.jenisPelan}"/>
                                <s:hidden name="kodMukimDel" value="${line.kodMukim}"/>
                                <s:hidden name="kodSeksyenDel" value="${line.kodSeksyen}"/>
                                <s:hidden name="noLotDel" value="${line.noLot}"/>
                                
                        </display:table> 
                        <c:if test="${fn:length(actionBean.listpelanGIS) > 0}">
                            <p align="center"><s:submit name="delete" value="delete" class="btn"/>&nbsp;</p>
                        </c:if>


                    </div>
                </fieldset>

            </fieldset>
        </div>

    </div> 
</s:form>
