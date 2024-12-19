<%-- 
    Document   : carian_permohonanCatitTanahMCL
    Created on : Aug 17, 2010, 3:35:57 PM
    Author     : afham
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
      $(document).ready( function() {


        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });
    function validate()
{
    a = document.test.id ;
  
  if (document.test.id.value==null||document.test.id.value=="")
    {

    alert("Sila masukkan id permohonan");
    a.focus()
    return false;
    }
  else
    {
     
    return true ;
    }
  
}





</script>

<s:form beanclass="etanah.view.stripes.pelupusan.CarianPermohonanActionBean" name="test">
    <s:errors/>
    <s:messages/>
    <div class="subtitle1">
        <c:if test="${!status}">
        <fieldset class="aras1">
            <legend>Carian Permohonan</legend>

            <p>
                <label for="id Permohonan">Id Permohonan:</label>
                <s:text name="id" value="id" id="id" class="required"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <%--<s:submit name="cariPermohonan" value="Cari" class="btn" onclick=""/>--%>
                <s:button name="cariPermohonan" id="cari" value="Cari" class="btn" onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>
                <%--<s:submit name="cariPermohonan" id="cari" value="Test" class="btn" onclick="return validate_required(this.form)"/>--%>
                  
            </p>
        </fieldset>
        </c:if>
        </div>
    <div class="content" align="center">
        <c:if test="${status}">
        <fieldset class="aras1">
            <legend>Status ${actionBean.permohonan.kodUrusan.nama} </legend>
            <s:hidden name="id2" value="id"/>
            <table border="1" align="center">

                <tr>
                    <td>Status Keputusan</td>
                    <td><c:if test="${actionBean.permohonan.keputusan.nama ne null}">${actionBean.permohonan.keputusan.nama}</c:if>
                        <c:if test="${actionBean.permohonan.keputusan.nama eq null}">Tiada Maklumat</c:if></td>
                </tr>
                 <tr>
                    <td>Tarikh Keputusan</td>
                    <td><c:if test="${actionBean.permohonan.tarikhKeputusan ne null}">${actionBean.permohonan.tarikhKeputusan}</c:if>
                        <c:if test="${actionBean.permohonan.tarikhKeputusan eq null}">Tiada Maklumat</c:if></td>
                </tr>
                 <tr>
                    <td>Keputusan Oleh</td>
                    <td><c:if test="${actionBean.permohonan.keputusanOleh.nama ne null}">${actionBean.permohonan.keputusanOleh.nama}</c:if>
                        <c:if test="${actionBean.permohonan.keputusanOleh.nama eq null}">Tiada Maklumat</c:if></td>
                </tr>
<!--                 <tr>
                    <td>Pemohon</td>
                    <td><c:if test="${actionBean.pemohon.pihak.nama ne null}">${actionBean.pemohon.pihak.nama} <br>
                                                                              ${actionBean.pemohon.pihak.noPengenalan} </c:if>
                        <c:if test="${actionBean.pemohon.pihak.nama eq null}">Tiada Maklumat</c:if></td>
                </tr>-->
            </table>
                </fieldset><br><br>
                <fieldset class="aras1">
                    <legend>Hakmilik Permohonan</legend>
                 <display:table class="tablecloth" name="${actionBean.hmpList}" cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                 </display:table>
                <br><br>
                <c:if test="${!simpan}">
                 <s:button name="simpanPermohonanSblm" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                 <s:button name="cariSemula" id="cari" value="Cari Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </fieldset>

        
        </c:if>
    </div>


</s:form>
